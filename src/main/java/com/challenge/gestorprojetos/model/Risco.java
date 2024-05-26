package com.challenge.gestorprojetos.model;

public enum Risco {

    ALTO("Risco alto"),
    MEDIO("Risco médio"),
    BAIXO("Risco baixo");

    private final String descricao;

    Risco(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
