package br.com.algaworks.algamoneyapi.repository.lancamento;

import br.com.algaworks.algamoneyapi.model.Lancamento;
import br.com.algaworks.algamoneyapi.repository.filter.LancamentoFilter;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteriaQuery = builder.createQuery(Lancamento.class);
        Root<Lancamento> lancamentoRoot = criteriaQuery.from(Lancamento.class);

        Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, lancamentoRoot);
        criteriaQuery.where(predicates);

        TypedQuery<Lancamento> query = manager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    private Predicate[] criarRestricoes(
            LancamentoFilter lancamentoFilter,
            CriteriaBuilder builder,
            Root<Lancamento> lancamentoRoot) {

        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(lancamentoFilter.getDescricao())) {
            predicates.add(builder.like(
               builder.lower(lancamentoRoot.get("descricao")),
               "%" + lancamentoFilter.getDescricao().toLowerCase() + "%"
            ));
        }

        if ( lancamentoFilter.getDataVencimentoDe() != null) {
            predicates.add(builder.greaterThanOrEqualTo(
                    lancamentoRoot.get("dataVencimento"), lancamentoFilter.getDataVencimentoDe()
            ));
        }

        if ( lancamentoFilter.getDataVencimentoAte() != null) {
            predicates.add(builder.lessThanOrEqualTo(
                    lancamentoRoot.get("dataVencimento"), lancamentoFilter.getDataVencimentoAte()
            ));
        }

        return predicates.stream().toArray(Predicate[]::new);
    }
}
