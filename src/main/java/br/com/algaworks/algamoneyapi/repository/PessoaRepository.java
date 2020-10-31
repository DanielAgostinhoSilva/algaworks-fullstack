package br.com.algaworks.algamoneyapi.repository;

import br.com.algaworks.algamoneyapi.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByCodigoAndAtivoEquals(Long codigo, Boolean ativo);
}
