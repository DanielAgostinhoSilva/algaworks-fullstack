package br.com.algaworks.algamoneyapi.service;

import br.com.algaworks.algamoneyapi.model.Pessoa;
import br.com.algaworks.algamoneyapi.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;

    public Pessoa atualizar(Long codigo, Pessoa pessoa) {
        Pessoa pessoaEncontrada = buscarPessoaPeloCodigo(codigo);
        BeanUtils.copyProperties(pessoa, pessoaEncontrada, "codigo");
        return pessoaRepository.save(pessoaEncontrada);
    }

    public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
        Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
        pessoaSalva.setAtivo(ativo);
        pessoaRepository.save(pessoaSalva);
    }

    public Pessoa buscarPessoaPeloCodigo(Long codigo) {
        return pessoaRepository.findById(codigo).orElseThrow( () -> {
            throw new EmptyResultDataAccessException(1);
        });
    }
}
