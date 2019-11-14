package br.com.basecmp.sisgaragem.infrascructure.repository;

import br.com.basecmp.sisgaragem.domain.model.Pedido;
import br.com.basecmp.sisgaragem.domain.repository.PedidoRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PedidoRepositoryImpl implements PedidoRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Pedido> find(String nome, String dataInicial, String dataFinal) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        var predicates = new ArrayList<Predicate>();

        if(StringUtils.hasText(nome)) {
            predicates.add(criteriaBuilder.like(root.join("usuarioRemetente").get("nomeCompleto")
                    , "%" + nome + "%"));
        }

        if (StringUtils.hasText(dataInicial)) {
            LocalDate dateInicialFormatada = LocalDate.parse(dataInicial, formatter);
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dataDeEmbarque"), dateInicialFormatada));
        }

        if (StringUtils.hasText(dataFinal)) {
            LocalDate dateFinalFormatada = LocalDate.parse(dataFinal, formatter);
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataDeEmbarque"), dateFinalFormatada));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Pedido> query = manager.createQuery(criteriaQuery);
        return query.getResultList();
    }

}
