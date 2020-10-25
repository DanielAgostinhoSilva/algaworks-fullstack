package br.com.algaworks.algamoneyapi.resource;

import br.com.algaworks.algamoneyapi.event.RecursoCriadoEvent;
import br.com.algaworks.algamoneyapi.model.Categoria;
import br.com.algaworks.algamoneyapi.model.Pessoa;
import br.com.algaworks.algamoneyapi.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    private PessoaRepository pessoaRepository;
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoa.getCodigo()));
        return pessoaSalva;
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<Pessoa> optionalPessoa = pessoaRepository.findById(codigo);
        return optionalPessoa.isPresent() ?
                ResponseEntity.ok(optionalPessoa.get()) : ResponseEntity.notFound().build();
    }
}
