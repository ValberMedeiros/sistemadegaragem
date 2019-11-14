package br.com.basecmp.sisgaragem.domain.model.enums;

public enum StatusPedido {

	APROVACAO("Aguardando aprovação"),
	VIATURA("Aguardando Viatura"),
	MOTORISTA("Aguardando Motorista"),
	PRONTO("Pedido deferido"),
	REJEITADO("Pedido rejeitado");

	private String descricao;

	StatusPedido(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
