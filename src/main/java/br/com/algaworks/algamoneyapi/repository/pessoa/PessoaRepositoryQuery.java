package br.com.algaworks.algamoneyapi.repository.pessoa;

import br.com.algaworks.algamoneyapi.model.Lancamento;
import br.com.algaworks.algamoneyapi.model.Pessoa;
import br.com.algaworks.algamoneyapi.repository.filter.LancamentoFilter;
import br.com.algaworks.algamoneyapi.repository.filter.PessoaFilter;
import br.com.algaworks.algamoneyapi.repository.projection.ResumoLancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaRepositoryQuery {
    Page<Pessoa> filtrar(PessoaFilter pessoaFilter, Pageable pageable);
}
