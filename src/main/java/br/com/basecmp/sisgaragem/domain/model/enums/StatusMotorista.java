package br.com.basecmp.sisgaragem.domain.model.enums;

public enum StatusMotorista {
	
	PRONTO("Pronto"),
	MISSAO("Missao"),
	FERIAS("Ferias"),
	DISPENSADO("Dispensado"),
	ATESTADO("Atestado");
	
	public String descricao;

	StatusMotorista(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}	
}
