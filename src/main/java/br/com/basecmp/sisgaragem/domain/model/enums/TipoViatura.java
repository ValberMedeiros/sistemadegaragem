package br.com.basecmp.sisgaragem.domain.model.enums;

public enum TipoViatura {
	
	OPERACIONAL("Viatura Operacional"),
	ADMINISTRATIVA("Viatura Administrativa");
	
	private String descricao;
	
	private TipoViatura(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
