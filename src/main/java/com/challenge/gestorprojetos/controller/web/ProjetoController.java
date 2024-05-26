package com.challenge.gestorprojetos.controller.web;

import com.challenge.gestorprojetos.model.Item;
import com.challenge.gestorprojetos.model.Membro;
import com.challenge.gestorprojetos.model.Projeto;
import com.challenge.gestorprojetos.model.Status;
import com.challenge.gestorprojetos.service.MembroService;
import com.challenge.gestorprojetos.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/projetos")
public class ProjetoController {

    private final ProjetoService projetoService;
    private final MembroService membroService;

    @Autowired
    public ProjetoController(ProjetoService projetoService, MembroService membroService) {
        this.projetoService = projetoService;
        this.membroService = membroService;
    }

    @GetMapping
    public String listarProjetos(Model model) {
        List<Projeto> projetos = projetoService.recuperarProjetos();
        model.addAttribute("projetos", projetos);
        return "lista-projetos";
    }

    @GetMapping("/novo")
    public String novoProjeto(Model model) {
        carregarModelo(model, null);
        return "formulario-projeto";
    }

    @PostMapping
    public String salvarProjeto(@ModelAttribute Projeto projeto, @RequestParam(name = "funcionarios", required = false) List<String> funcionariosSelecionados) {
        var idsFuncionariosSelecionados = Optional.ofNullable(funcionariosSelecionados).map(f -> f.stream().map(Long::valueOf).toList()).orElse(Collections.emptyList());
        var funcinariosSelecionados = membroService.membrosPorId(idsFuncionariosSelecionados);
        projeto.setFuncionarios(funcinariosSelecionados);
        projetoService.salvarProjeto(projeto);
        return "redirect:/projetos";
    }

    @GetMapping("/editar/{id}")
    public String editarProjeto(@PathVariable Long id, Model model) {
        Projeto projeto = projetoService.projetoPorId(id);
        carregarModelo(model, projeto);
        return "formulario-projeto";
    }

    @GetMapping("/{id}")
    public String detalharProjeto(@PathVariable Long id, Model model) {
        Projeto projeto = projetoService.projetoPorId(id);
        String funcionarios = projeto.getFuncionarios().stream().map(Membro::toString).collect(Collectors.joining(", "));
        model.addAttribute("projeto", projeto);
        model.addAttribute("funcionarios", funcionarios);
        return "detalhes-projeto";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        Projeto projeto = projetoService.projetoPorId(id);
        projetoService.excluirProjeto(projeto);
        return "redirect:/projetos";
    }

    private void carregarModelo(Model model, Projeto projeto) {
        var funcionarios = membroService.recuperarFuncionarios().stream().map(f -> {
            var selecionado = Optional.ofNullable(projeto)
                    .map(p -> p.getFuncionarios().contains(f))
                    .orElse(false);
            return new Item(f.getId().toString(), f.getNome(), selecionado);
        }).toList();

        model.addAttribute("projeto", Optional.ofNullable(projeto).orElse(new Projeto()));
        model.addAttribute("todosStatus", Status.values());
        model.addAttribute("membros", membroService.recuperarMembros());
        model.addAttribute("funcionarios", funcionarios);
    }
}
