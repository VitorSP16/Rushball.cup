package br.com.rushball.rushball.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.rushball.rushball.service.SelecaoService;
import br.com.rushball.rushball.service.JogadorService;

@Controller
@RequestMapping("/selecoes")
public class SelecaoController {

    private final SelecaoService selecaoService;
    private final JogadorService jogadorService;

    public SelecaoController(
            SelecaoService selecaoService,
            JogadorService jogadorService) {

        this.selecaoService = selecaoService;
        this.jogadorService = jogadorService;
    }

    @GetMapping
    public String listar(Model model) {

        model.addAttribute("selecoes", selecaoService.listarTodas());

        return "selecao/lista";
    }

    @GetMapping("/{id}")
    public String detalhes(@PathVariable Long id, Model model) {

        model.addAttribute("selecao", selecaoService.buscarPorId(id));

        model.addAttribute("jogadores", jogadorService.buscarPorSelecaoId(id));

        return "selecao/detalhes";
    }
}