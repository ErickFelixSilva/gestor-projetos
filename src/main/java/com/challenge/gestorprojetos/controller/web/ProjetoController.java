package com.challenge.gestorprojetos.controller.web;

import com.challenge.gestorprojetos.model.*;
import com.challenge.gestorprojetos.service.PessoaService;
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
    private final PessoaService pessoaService;

    @Autowired
    public ProjetoController(ProjetoService projetoService, PessoaService pessoaService) {
        this.projetoService = projetoService;
        this.pessoaService = pessoaService;
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
    public String salvarProjeto(@ModelAttribute Projeto projeto,
                                @RequestParam(name = "funcionarios", required = false) List<String> funcionariosSelecionados,
                                @RequestParam(name = "valorOrcamento", required = false) String valorOrcamento) {
        var idsFuncionariosSelecionados = Optional.ofNullable(funcionariosSelecionados).map(f -> f.stream().map(Long::valueOf).toList()).orElse(Collections.emptyList());
        var funcinariosSelecionados = pessoaService.pessoasPorId(idsFuncionariosSelecionados);
        projeto.setMembros(funcinariosSelecionados);
        projeto.setOrcamento(removerFormatoMonetario(valorOrcamento));
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
        String membros = projeto.getMembros().stream().map(Pessoa::toString).collect(Collectors.joining(", "));
        model.addAttribute("projeto", projeto);
        model.addAttribute("membros", membros);
        return "detalhes-projeto";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        Projeto projeto = projetoService.projetoPorId(id);
        projetoService.excluirProjeto(projeto);
        return "redirect:/projetos";
    }

    private void carregarModelo(Model model, Projeto projeto) {
        var funcionarios = pessoaService.recuperarFuncionarios().stream().map(f -> {
            var selecionado = Optional.ofNullable(projeto)
                    .map(p -> p.getMembros().contains(f))
                    .orElse(false);
            return new Item(f.getId().toString(), f.getNome(), selecionado);
        }).toList();
        var orcamento = Optional.ofNullable(projeto).map(Projeto::getOrcamento).orElse(0f);

        model.addAttribute("projeto", Optional.ofNullable(projeto).orElse(new Projeto()));
        model.addAttribute("todosStatus", Status.values());
        model.addAttribute("riscos", Risco.values());
        model.addAttribute("gerentes", pessoaService.recuperarGerentes());
        model.addAttribute("valorOrcamento", orcamento.toString());
        model.addAttribute("funcionarios", funcionarios);
    }

    private Float removerFormatoMonetario(String valorMonetario) {
        if (valorMonetario != null) {
            valorMonetario = valorMonetario.replaceAll("[^\\d,]", "").replace(",", ".");
            try {
                return Float.parseFloat(valorMonetario);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Valor inválido: " + valorMonetario);
            }
        }
        return null;
    }
}
