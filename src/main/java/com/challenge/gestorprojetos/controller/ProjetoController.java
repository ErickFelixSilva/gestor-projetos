package com.challenge.gestorprojetos.controller;

import com.challenge.gestorprojetos.model.Projeto;
import com.challenge.gestorprojetos.model.Status;
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
        model.addAttribute("todosStatus", Status.values());
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
        model.addAttribute("todosStatus", Status.values());
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
        Projeto projeto = projetoService.projetoPorId(id);
        if (projeto.getStatus() == Status.INICIADO || projeto.getStatus() == Status.EM_ANDAMENTO ||
                projeto.getStatus() == Status.ENCERRADO) {
            throw new IllegalArgumentException("Não é possível excluir projeto com o status: " + projeto.getStatus().getDescricao());
        }
        projetoService.excluirProjeto(projeto);
        return "redirect:/projetos";
    }
}
