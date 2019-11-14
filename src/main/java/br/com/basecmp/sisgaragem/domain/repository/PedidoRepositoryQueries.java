package br.com.basecmp.sisgaragem.domain.repository;

import br.com.basecmp.sisgaragem.domain.model.Pedido;

import java.util.List;

public interface PedidoRepositoryQueries {

    List<Pedido> find(String nome, String dataInicial, String dataFinal);

}
