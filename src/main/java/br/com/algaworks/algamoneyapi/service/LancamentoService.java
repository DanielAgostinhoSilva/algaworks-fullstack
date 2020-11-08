package br.com.algaworks.algamoneyapi.service;

import br.com.algaworks.algamoneyapi.model.Lancamento;
import br.com.algaworks.algamoneyapi.model.Pessoa;
import br.com.algaworks.algamoneyapi.repository.LancamentoRepository;
import br.com.algaworks.algamoneyapi.repository.PessoaRepository;
import br.com.algaworks.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class LancamentoService {

    private PessoaRepository pessoaRepository;
    private LancamentoRepository lancamentoRepository;

    public Lancamento salvar(Lancamento lancamento) {
        Pessoa pessoaEncontrada = pessoaRepository.findByCodigoAndAtivoEquals(
                lancamento.getPessoa().getCodigo(),
                Boolean.TRUE
        ).orElseThrow(PessoaInexistenteOuInativaException::new);

        return lancamentoRepository.save(lancamento);
    }

    public Lancamento atualizar(Long codigo, Lancamento lancamento) {
        Lancamento lancamentoEncontrado = this.buscarOuFalhar(codigo);
        BeanUtils.copyProperties(lancamento, lancamentoEncontrado, "codigo");
        return this.salvar(lancamento);
    }

    public void removerLancamento(Long codigo) {
        Lancamento lancamento = this.buscarOuFalhar(codigo);
        lancamentoRepository.delete(lancamento);
    }

    public Lancamento buscarOuFalhar(Long codigo) {
        return lancamentoRepository.findById(codigo).orElseThrow( () -> {
            throw new EmptyResultDataAccessException(1);
        });
    }
}
