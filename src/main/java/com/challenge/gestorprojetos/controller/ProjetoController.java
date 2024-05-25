package com.challenge.gestorprojetos.controller;

import com.challenge.gestorprojetos.model.Projeto;
import com.challenge.gestorprojetos.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String listarProjetos(Model model) {
        List<Projeto> projetos = projetoService.recuperarProjetos();
        model.addAttribute("projetos", projetos);
        return "lista-projetos";
    }

    @GetMapping("/novo")
    public String novoProjeto(Model model) {
        model.addAttribute("projeto", new Projeto());
        return "formulario-projeto";
    }

    @PostMapping
    public String salvarProjeto(@ModelAttribute Projeto projeto) {
        projetoService.salvarProjeto(projeto);
        return "redirect:/projetos";
    }

    @GetMapping("/editar/{id}")
    public String editarProjeto(@PathVariable Long id, Model model) {
        Projeto projeto = projetoService.projetoPorId(id);
        model.addAttribute("projeto", projeto);
        return "formulario-projeto";
    }

    @GetMapping("/{id}")
    public String detalharProjeto(@PathVariable Long id, Model model) {
        Projeto projeto = projetoService.projetoPorId(id);
        model.addAttribute("projeto", projeto);
        return "detalhes-projeto";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        projetoService.excluirProjeto(id);
        return "redirect:/projetos";
    }
}
