package com.example.portfolio.controller.api;

import com.challenge.gestorprojetos.model.Membro;
import com.challenge.gestorprojetos.service.MembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/membros")
public class MembroRestController {

    private final MembroService membroService;

    @Autowired
    public MembroRestController(MembroService membroService) {
        this.membroService = membroService;
    }

    @GetMapping
    public List<Membro> recuperar() {
        return membroService.recuperarMembros();
    }

    @PostMapping
    public ResponseEntity<Membro> adicionarMembro(@RequestBody Membro membro) {
        Membro savedMember = membroService.salvarMembro(membro);
        return ResponseEntity.ok(savedMember);
    }
}
