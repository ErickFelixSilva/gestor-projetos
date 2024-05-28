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
    PessoaServiceImpl pessoaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Carlos Andrade");

        assertDoesNotThrow(() -> pessoaService.salvarPessoa(pessoa));
        verify(pessoaRepository, times(1)).save(pessoa);
    }

    @Test
    void naoDeveSalvarPessoa() {
        Pessoa pessoa = new Pessoa();

        assertThrows(IllegalArgumentException.class, () -> pessoaService.salvarPessoa(pessoa));
        verify(pessoaRepository, never()).save(any(Pessoa.class));
    }

    @Test
    void deveRecuperarPessoaPorId() {
        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.of(new Pessoa()));

        assertDoesNotThrow(() -> pessoaService.pessoarPorId(1L));
        verify(pessoaRepository, times(1)).findById(anyLong());
    }

    @Test
    void naoDeveRecuperarPessoaInexistentePorId() {
        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(PessoaNaoEncontradaException.class, () -> pessoaService.pessoarPorId(1L));
        verify(pessoaRepository, times(1)).findById(anyLong());
    }

    @Test
    void deveRecuperarPessoasPorId() {
        assertDoesNotThrow(() -> pessoaService.pessoasPorId(List.of(1L, 2L)));
        verify(pessoaRepository, times(1)).findAllById(anyCollection());
    }

    @Test
    void deveRecuperarGerentes() {
        assertDoesNotThrow(() -> pessoaService.recuperarGerentes());
        verify(pessoaRepository, times(1)).findByGerenteTrue();
    }

    @Test
    void deveRecuperarPessoasFuncionarios() {
        assertDoesNotThrow(() -> pessoaService.recuperarFuncionarios());
        verify(pessoaRepository, times(1)).findByFuncionarioTrue();
    }

    @Test
    void deveExcluirPessoa() {
        assertDoesNotThrow(() -> pessoaService.excluirPessoa(new Pessoa()));
        verify(pessoaRepository, times(1)).delete(any(Pessoa.class));
    }

}
