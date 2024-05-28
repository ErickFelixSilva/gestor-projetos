package com.challenge.gestorprojetos.controller.api;

import com.challenge.gestorprojetos.model.Pessoa;
import com.challenge.gestorprojetos.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaRestController {

    private final PessoaService pessoaService;

    @Autowired
    public PessoaRestController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<Pessoa> adicionarMembro(@RequestBody Pessoa membro) {
        Pessoa savedMember = pessoaService.salvarPessoa(membro);
        return ResponseEntity.ok(savedMember);
    }
}
