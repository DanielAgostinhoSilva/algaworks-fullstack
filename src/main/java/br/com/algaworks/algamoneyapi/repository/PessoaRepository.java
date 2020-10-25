package br.com.algaworks.algamoneyapi.repository;

import br.com.algaworks.algamoneyapi.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
