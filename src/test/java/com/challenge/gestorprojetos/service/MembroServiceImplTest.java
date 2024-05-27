package com.challenge.gestorprojetos.service;

import com.challenge.gestorprojetos.exception.MembroNaoEncontradoException;
import com.challenge.gestorprojetos.model.Membro;
import com.challenge.gestorprojetos.repository.MembroRepository;
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

class MembroServiceImplTest {

    @Mock
    MembroRepository membroRepository;

    @InjectMocks
    MembroServiceImpl membroService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarMembro() {
        Membro membro = new Membro();
        membro.setNome("Carlos Andrade");

        assertDoesNotThrow(() -> membroService.salvarMembro(membro));
        verify(membroRepository, times(1)).save(membro);
    }

    @Test
    void naoDeveSalvarMembro() {
        Membro membro = new Membro();

        assertThrows(IllegalArgumentException.class, () -> membroService.salvarMembro(membro));
        verify(membroRepository, never()).save(any(Membro.class));
    }

    @Test
    void deveRecuperarMembroPorId() {
        when(membroRepository.findById(anyLong())).thenReturn(Optional.of(new Membro()));

        assertDoesNotThrow(() -> membroService.membroPorId(1L));
        verify(membroRepository, times(1)).findById(anyLong());
    }

    @Test
    void naoDeveRecuperarMembroInexistentePorId() {
        when(membroRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(MembroNaoEncontradoException.class, () -> membroService.membroPorId(1L));
        verify(membroRepository, times(1)).findById(anyLong());
    }

    @Test
    void deveRecuperarMembrosPorId() {
        assertDoesNotThrow(() -> membroService.membrosPorId(List.of(1L, 2L)));
        verify(membroRepository, times(1)).findAllById(anyCollection());
    }

    @Test
    void deveExcluirMembro() {
        assertDoesNotThrow(() -> membroService.excluirMembro(new Membro()));
        verify(membroRepository, times(1)).delete(any(Membro.class));
    }

}
