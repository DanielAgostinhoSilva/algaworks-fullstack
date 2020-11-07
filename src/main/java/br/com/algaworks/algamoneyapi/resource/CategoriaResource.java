package br.com.algaworks.algamoneyapi.resource;

import br.com.algaworks.algamoneyapi.event.RecursoCriadoEvent;
import br.com.algaworks.algamoneyapi.model.Categoria;
import br.com.algaworks.algamoneyapi.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    private CategoriaRepository categoriaRepository;
    private ApplicationEventPublisher publisher;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_CADASTRAR_CATEGORIA') and #oauth2.hasScope('write')")
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
        Categoria categoriaSalva = categoriaRepository.save(categoria);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));
        return categoriaSalva;
    }

    @GetMapping("/{codigo}")
    @PreAuthorize("hasAnyAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
    public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(codigo);
        return optionalCategoria.isPresent() ?
                ResponseEntity.ok(optionalCategoria.get()) : ResponseEntity.notFound().build();
    }
}
