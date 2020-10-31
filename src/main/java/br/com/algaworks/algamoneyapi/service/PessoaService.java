package br.com.algaworks.algamoneyapi.service;

import br.com.algaworks.algamoneyapi.model.Pessoa;
import br.com.algaworks.algamoneyapi.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;

    @Transactional
    public Pessoa Atualiza(Long codigo, Pessoa pessoa) {
        Pessoa pessoaEncontrada = this.buscarOuFalhar(codigo);
        BeanUtils.copyProperties(pessoa, pessoaEncontrada, "codigo");
        return pessoaRepository.save(pessoaEncontrada);
    }

    @Transactional
    public void ativar(Long codigo, Boolean ativo) {
        Pessoa pessoa = buscarOuFalhar(codigo);
        pessoa.setAtivo(ativo);
    }

    public Pessoa buscarOuFalhar(Long codigo) {
        return  pessoaRepository.findById(codigo).orElseThrow( () -> {
            throw new EmptyResultDataAccessException(1);
        });
    }
}
