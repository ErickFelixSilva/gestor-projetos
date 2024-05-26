package com.challenge.gestorprojetos.model;

public class Item {
    private String id;
    private String nome;
    private boolean selecionado;

    public Item(String id, String nome, boolean selecionado) {
        this.id = id;
        this.nome = nome;
        this.selecionado = selecionado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }
}
