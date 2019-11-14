package br.com.basecmp.sisgaragem.domain.repository;

import br.com.basecmp.sisgaragem.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long>, PedidoRepositoryQueries {
    List<Pedido> findByDestinoIgnoreCaseContainingOrderByDataDeEmbarqueDesc(String pesquisa);

    @Query("select u from Pedido u where status_pedido =2")
    List<Pedido> findAllByStatusPedidoAprovadosOrderByDataDeEmbarqueDesc();

    @Query("select u from Pedido u where status_pedido =1")
    List<Pedido> findAllByStatusPedidoMotoristaOrderByDataDeEmbarqueDesc();

    @Override
    List<Pedido> find(String nome, String dataInicial, String dataFinal);
}
