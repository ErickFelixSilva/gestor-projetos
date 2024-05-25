package com.challenge.gestorprojetos.controller;

import com.challenge.gestorprojetos.model.Projeto;
import com.challenge.gestorprojetos.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projetos")
public class ProjetoController {

    private final ProjetoService projetoService;

    @Autowired
    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping
    public String listarProjetos() {
        List<Projeto> projetos = projetoService.recuperarProjetos();
        return "";
    }

    @GetMapping("/novo")
    public String novoProjeto() {
        return "";
    }

    @PostMapping
    public String salvarProjeto(@ModelAttribute Projeto projeto) {
        projetoService.salvarProjeto(projeto);
        return "";
    }

    @GetMapping("/editar/{id}")
    public String editarProjeto(@PathVariable Long id) {
        Projeto projeto = projetoService.projetoPorId(id);
        return "";
    }

    @GetMapping("/{id}")
    public String detalharProjeto(@PathVariable Long id) {
        Projeto projeto = projetoService.projetoPorId(id);
        return "";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        projetoService.excluirProjeto(id);
        return "";
    }
}
