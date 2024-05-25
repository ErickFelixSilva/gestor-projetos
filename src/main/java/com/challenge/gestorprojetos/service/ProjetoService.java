package com.challenge.gestorprojetos.service;

import com.challenge.gestorprojetos.model.Projeto;

import java.util.List;

public interface ProjetoService {
    List<Projeto> recuperarProjetos();
    Projeto projetoPorId(Long id);
    void salvarProjeto(Projeto projeto);
    void excluirProjeto(Projeto projeto);
}
