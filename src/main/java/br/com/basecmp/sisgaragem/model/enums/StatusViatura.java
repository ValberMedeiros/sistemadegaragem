package br.com.basecmp.sisgaragem.model.enums;

public enum StatusViatura {

	BAIXADA("Baixada"),
	MISSAO("Em missão"),
	PRONTA("Pronta");
	
	private String descricao;
	
	StatusViatura(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
