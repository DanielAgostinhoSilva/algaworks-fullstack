package br.com.algaworks.algamoneyapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "lancamento")
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long codigo;

    @NotNull
    private String descricao;

    @Column(name = "data_vencimento")
    @JsonFormat(pattern =  "dd/MM/yyyy")
    private LocalDate dataVencimento;

    @Column(name = "data_pagamento")
    @JsonFormat(pattern =  "dd/MM/yyyy")
    private LocalDate dataPagamento;

    @NotNull
    private BigDecimal valor;

    private String observacao;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoLancamento tipo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "codigo_categoria")
    private Categoria categoria;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "codigo_pessoa")
    private Pessoa pessoa;
}
