package com.challenge.gestorprojetos.service;

import com.challenge.gestorprojetos.exception.ProjetoNaoEncontradoException;
import com.challenge.gestorprojetos.model.Projeto;
import com.challenge.gestorprojetos.model.Status;
import com.challenge.gestorprojetos.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (projeto.getNome() == null || projeto.getNome().isEmpty()) {
            throw new IllegalArgumentException("É necessário informar um nome para o projeto");
        }
        if (projeto.getStatus() == null) {
            throw new IllegalArgumentException("É necessário informar um status para o projeto");
        }
        if (projeto.getGerenteResponsavel() == null) {
            throw new IllegalArgumentException("É necessário informar um gerente para o projeto");
        }
        if (projeto.getDataInicio() != null && projeto.getDataInicio().isAfter(projeto.getPrevisaoTermino())) {
            throw new IllegalArgumentException("A data inicial do projeto deve ser anterior à data prevista de término");
        }
        if (projeto.getDataRealTermino() != null && projeto.getDataInicio().isAfter(projeto.getDataRealTermino())) {
            throw new IllegalArgumentException("A data inicial do projeto deve ser anterior à data real de término");
        }
        projetoRepository.save(projeto);
    }

    @Override
    public void excluirProjeto(Projeto projeto) {
        if (projeto.getStatus() == Status.INICIADO || projeto.getStatus() == Status.EM_ANDAMENTO ||
                projeto.getStatus() == Status.ENCERRADO) {
            throw new IllegalArgumentException("Não é possível excluir projeto com o status: " + projeto.getStatus().getDescricao());
        }
        projetoRepository.delete(projeto);
    }
}
