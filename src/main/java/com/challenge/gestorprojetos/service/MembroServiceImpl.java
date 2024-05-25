package com.challenge.gestorprojetos.service;

import com.challenge.gestorprojetos.exception.MembroNaoEncontradoException;
import com.challenge.gestorprojetos.exception.ProjetoNaoEncontradoException;
import com.challenge.gestorprojetos.model.Membro;
import com.challenge.gestorprojetos.repository.MembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembroServiceImpl implements MembroService {

    private final MembroRepository membroRepository;

    @Autowired
    public MembroServiceImpl(MembroRepository membroRepository) {
        this.membroRepository = membroRepository;
    }

    @Override
    public List<Membro> recuperarMembros() {
        return membroRepository.findAll();
    }

    @Override
    public Membro membroPorId(Long id) {
        return membroRepository.findById(id).orElseThrow(() ->
                new MembroNaoEncontradoException("Não foi possível recuperar o membro de id: " + id));
    }

    @Override
    public Membro salvarMembro(Membro membro) {
        return membroRepository.save(membro);
    }

    @Override
    public void excluirMembro(Membro membro) {
        membroRepository.delete(membro);
    }
}
