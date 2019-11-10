package br.com.basecmp.sisgaragem.model.enums;

public enum Finalidade {

    MATERIAL("Transporte de Material"),
    PESSOAL("Transporte de Pessoal");

    private String descricao;

    Finalidade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
