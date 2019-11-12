package br.com.basecmp.sisgaragem.repository;

import br.com.basecmp.sisgaragem.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByDestinoIgnoreCaseContainingOrderByDataDeEmbarqueDesc(String pesquisa);

    @Query("select u from Pedido u where status_pedido =2")
    List<Pedido> findAllByStatusPedidoAprovadosOrderByDataDeEmbarqueDesc();

    @Query("select u from Pedido u where status_pedido =1")
    List<Pedido> findAllByStatusPedidoMotoristaOrderByDataDeEmbarqueDesc();
}
