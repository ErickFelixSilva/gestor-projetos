package com.challenge.gestorprojetos.service;

import com.challenge.gestorprojetos.exception.MembroNaoEncontradoException;
import com.challenge.gestorprojetos.model.Membro;
import com.challenge.gestorprojetos.repository.MembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembroServiceImpl implements MembroService {

    private final MembroRepository membroRepository;
    private static final String CARGO_FUNCIONARIO = "Funcionário";

    @Autowired
    public MembroServiceImpl(MembroRepository membroRepository) {
        this.membroRepository = membroRepository;
    }

    @Override
    public List<Membro> recuperarMembros() {
        return membroRepository.findAll();
    }

    @Override
    public List<Membro> recuperarFuncionarios() { return membroRepository.findByCargo(CARGO_FUNCIONARIO); }

    @Override
    public Membro membroPorId(Long id) {
        return membroRepository.findById(id).orElseThrow(() ->
                new MembroNaoEncontradoException("Não foi possível recuperar o membro de id: " + id));
    }

    @Override
    public List<Membro> membrosPorId(List<Long> ids) {
        return membroRepository.findAllById(ids);
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
