package com.challenge.gestorprojetos.service;

import com.challenge.gestorprojetos.exception.PessoaNaoEncontradaException;
import com.challenge.gestorprojetos.model.Pessoa;
import com.challenge.gestorprojetos.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaServiceImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public List<Pessoa> recuperarGerentes() { return pessoaRepository.findByGerenteTrue(); }

    @Override
    public List<Pessoa> recuperarFuncionarios() { return pessoaRepository.findByFuncionarioTrue(); }

    @Override
    public Pessoa pessoarPorId(Long id) {
        return pessoaRepository.findById(id).orElseThrow(() ->
                new PessoaNaoEncontradaException("Não foi possível recuperar a pessoa de id: " + id));
    }

    @Override
    public List<Pessoa> pessoasPorId(List<Long> ids) {
        return pessoaRepository.findAllById(ids);
    }

    @Override
    public Pessoa salvarPessoa(Pessoa pessoa) {
        if (pessoa.getNome() == null || pessoa.getNome().isEmpty()) {
            throw new IllegalArgumentException("Para cadastrar uma pessoa é necessário especificar um nome");
        }
        return pessoaRepository.save(pessoa);
    }

    @Override
    public void excluirPessoa(Pessoa pessoa) {
        pessoaRepository.delete(pessoa);
    }
}
