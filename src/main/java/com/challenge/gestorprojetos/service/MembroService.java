package com.challenge.gestorprojetos.service;

import com.challenge.gestorprojetos.model.Membro;

import java.util.List;

public interface MembroService {
    List<Membro> recuperarMembros();

    List<Membro> recuperarFuncionarios();

    Membro membroPorId(Long id);
    List<Membro> membrosPorId(List<Long> ids);
    Membro salvarMembro(Membro membro);
    void excluirMembro(Membro membro);
}
