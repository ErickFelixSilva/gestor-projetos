package com.challenge.gestorprojetos.service;

import com.challenge.gestorprojetos.exception.ProjetoNaoEncontradoException;
import com.challenge.gestorprojetos.model.Pessoa;
import com.challenge.gestorprojetos.model.Projeto;
import com.challenge.gestorprojetos.model.Status;
import com.challenge.gestorprojetos.repository.ProjetoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ProjetoServiceImplTest {

    @Mock
    ProjetoRepository projetoRepository;

    @InjectMocks
    ProjetoServiceImpl projetoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarProjetoValido() {
        Projeto projeto = new Projeto();
        projeto.setNome("projeto-valido");
        projeto.setStatus(Status.EM_ANDAMENTO);
        projeto.setGerente(new Pessoa());

        assertDoesNotThrow(() -> projetoService.salvarProjeto(projeto));
        verify(projetoRepository, times(1)).save(projeto);
    }

    @Test
    void naoDeveSalvarProjetoInvalido() {
        Projeto projeto = new Projeto();

        assertThrows(IllegalArgumentException.class, () -> projetoService.salvarProjeto(projeto));
        verify(projetoRepository, never()).save(any(Projeto.class));
    }

    @Test
    void naoDeveSalvarProjetoComDatasInvalidas() {
        Projeto projeto = new Projeto();
        projeto.setNome("projeto-valido");
        projeto.setStatus(Status.EM_ANDAMENTO);
        projeto.setGerente(new Pessoa());
        projeto.setDataInicio(LocalDate.of(2024, 1, 10));
        projeto.setDataPrevisaoFim(LocalDate.of(2024, 1, 8));

        assertThrows(IllegalArgumentException.class, () -> projetoService.salvarProjeto(projeto));
        verify(projetoRepository, never()).save(any(Projeto.class));
    }

    @Test
    void deveRecuperarProjetos() {
        projetoService.recuperarProjetos();
        verify(projetoRepository, times(1)).findAll();
    }

    @Test
    void deveRecuperarProjetoPorId() {
        when(projetoRepository.findById(anyLong())).thenReturn(Optional.of(new Projeto()));

        projetoService.projetoPorId(1L);
        verify(projetoRepository, times(1)).findById(anyLong());
    }

    @Test
    void naoDeveRecuperarProjetoInexistentePorId() {
        when(projetoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ProjetoNaoEncontradoException.class, () -> projetoService.projetoPorId(1L));
        verify(projetoRepository, times(1)).findById(anyLong());
    }

    @Test
    void deveExcluirProjeto() {
        Projeto projeto = new Projeto();

        assertDoesNotThrow(() -> projetoService.excluirProjeto(projeto));
        verify(projetoRepository, times(1)).delete(any(Projeto.class));
    }

    @Test
    void naoDeveExcluirProjetoComStatusEspecifico() {
        Projeto projeto = new Projeto();
        projeto.setStatus(Status.EM_ANDAMENTO);

        assertThrows(IllegalArgumentException.class, () -> projetoService.excluirProjeto(projeto));
        verify(projetoRepository, never()).delete(any(Projeto.class));
    }
}
