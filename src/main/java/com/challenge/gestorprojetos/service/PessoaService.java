package com.challenge.gestorprojetos.service;

import com.challenge.gestorprojetos.model.Pessoa;

import java.util.List;

public interface PessoaService {
    List<Pessoa> recuperarFuncionarios();
    List<Pessoa> recuperarGerentes();
    Pessoa pessoarPorId(Long id);
    List<Pessoa> pessoasPorId(List<Long> ids);
    Pessoa salvarPessoa(Pessoa pessoa);
    void excluirPessoa(Pessoa pessoa);
}
