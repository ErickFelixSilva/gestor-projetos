package com.challenge.gestorprojetos.service;

import com.challenge.gestorprojetos.exception.ProjetoNaoEncontradoException;
import com.challenge.gestorprojetos.model.Projeto;
import com.challenge.gestorprojetos.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    private final ProjetoRepository projetoRepository;

    @Autowired
    public ProjetoServiceImpl(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    @Override
    public List<Projeto> recuperarProjetos() {
        return projetoRepository.findAll();
    }

    @Override
    public Projeto projetoPorId(Long id) {
        return projetoRepository.findById(id).orElseThrow(() ->
            new ProjetoNaoEncontradoException("Projeto com id %d não encontrado".formatted(id)));
    }

    @Override
    public void salvarProjeto(Projeto projeto) {
        projetoRepository.save(projeto);
    }

    @Override
    public void excluirProjeto(Projeto projeto) {
        projetoRepository.delete(projeto);
    }
}
