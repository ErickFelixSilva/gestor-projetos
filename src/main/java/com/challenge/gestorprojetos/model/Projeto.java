package com.challenge.gestorprojetos.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private LocalDate dataInicio;
    private LocalDate previsaoTermino;
    private LocalDate dataRealTermino;
    private Double orcamentoTotal;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "gerente_id")
    private Membro gerenteResponsavel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getPrevisaoTermino() {
        return previsaoTermino;
    }

    public void setPrevisaoTermino(LocalDate previsaoTermino) {
        this.previsaoTermino = previsaoTermino;
    }

    public LocalDate getDataRealTermino() {
        return dataRealTermino;
    }

    public void setDataRealTermino(LocalDate dataRealTermino) {
        this.dataRealTermino = dataRealTermino;
    }

    public Membro getGerenteResponsavel() {
        return gerenteResponsavel;
    }

    public void setGerenteResponsavel(Membro gerenteResponsavel) {
        this.gerenteResponsavel = gerenteResponsavel;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getOrcamentoTotal() {
        return orcamentoTotal;
    }

    public void setOrcamentoTotal(Double orcamentoTotal) {
        this.orcamentoTotal = orcamentoTotal;
    }
}
