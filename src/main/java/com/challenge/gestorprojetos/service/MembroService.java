package com.challenge.gestorprojetos.service;

import com.challenge.gestorprojetos.model.Membro;
import com.challenge.gestorprojetos.model.Projeto;

import java.util.List;

public interface MembroService {
    List<Membro> recuperarMembros();
    Membro membroPorId(Long id);
    Membro salvarMembro(Membro membro);
    void excluirMembro(Membro membro);
}
