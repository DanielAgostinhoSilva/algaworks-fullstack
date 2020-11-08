package br.com.algaworks.algamoneyapi.repository.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class PessoaFilter {
    private String nome;
}
