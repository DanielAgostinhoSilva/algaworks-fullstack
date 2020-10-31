package br.com.algaworks.algamoneyapi.repository.lancamento;

import br.com.algaworks.algamoneyapi.model.Lancamento;
import br.com.algaworks.algamoneyapi.repository.filter.LancamentoFilter;

import java.util.List;

public interface LancamentoRepositoryQuery {
    List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
}
