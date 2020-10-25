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
        Pessoa pessoaEncontrada = pessoaRepository.findById(codigo).orElseThrow( () -> {
            throw new EmptyResultDataAccessException(1);
        });
        BeanUtils.copyProperties(pessoa, pessoaEncontrada, "codigo");
        return pessoaRepository.save(pessoaEncontrada);
    }
}
