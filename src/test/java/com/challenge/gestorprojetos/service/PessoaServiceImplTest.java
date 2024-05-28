package com.challenge.gestorprojetos.service;

import com.challenge.gestorprojetos.exception.PessoaNaoEncontradaException;
import com.challenge.gestorprojetos.model.Pessoa;
import com.challenge.gestorprojetos.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class PessoaServiceImplTest {

    @Mock
    PessoaRepository pessoaRepository;

    @InjectMocks
    PessoaServiceImpl membroService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarMembro() {
        Pessoa membro = new Pessoa();
        membro.setNome("Carlos Andrade");

        assertDoesNotThrow(() -> membroService.salvarPessoa(membro));
        verify(pessoaRepository, times(1)).save(membro);
    }

    @Test
    void naoDeveSalvarMembro() {
        Pessoa membro = new Pessoa();

        assertThrows(IllegalArgumentException.class, () -> membroService.salvarPessoa(membro));
        verify(pessoaRepository, never()).save(any(Pessoa.class));
    }

    @Test
    void deveRecuperarMembroPorId() {
        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.of(new Pessoa()));

        assertDoesNotThrow(() -> membroService.pessoarPorId(1L));
        verify(pessoaRepository, times(1)).findById(anyLong());
    }

    @Test
    void naoDeveRecuperarMembroInexistentePorId() {
        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(PessoaNaoEncontradaException.class, () -> membroService.pessoarPorId(1L));
        verify(pessoaRepository, times(1)).findById(anyLong());
    }

    @Test
    void deveRecuperarMembrosPorId() {
        assertDoesNotThrow(() -> membroService.pessoasPorId(List.of(1L, 2L)));
        verify(pessoaRepository, times(1)).findAllById(anyCollection());
    }

    @Test
    void deveRecuperarMembros() {
        assertDoesNotThrow(() -> membroService.recuperarPessoas());
        verify(pessoaRepository, times(1)).findAll();
    }

    @Test
    void deveRecuperarMembrosFuncionarios() {
        assertDoesNotThrow(() -> membroService.recuperarFuncionarios());
        verify(pessoaRepository, times(1)).findByFuncionarioTrue();
    }

    @Test
    void deveExcluirMembro() {
        assertDoesNotThrow(() -> membroService.excluirPessoa(new Pessoa()));
        verify(pessoaRepository, times(1)).delete(any(Pessoa.class));
    }

}
