package br.com.rushball.rushball.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.rushball.rushball.service.JogadorService;
import br.com.rushball.rushball.service.SelecaoService;

@Controller
@RequestMapping("/jogadores")
public class JogadorController {

    private final JogadorService jogadorService;
    private final SelecaoService selecaoService;

    public JogadorController(
            JogadorService jogadorService,
            SelecaoService selecaoService) {

        this.jogadorService = jogadorService;
        this.selecaoService = selecaoService;
    }

    @GetMapping("/listar")
    public String listar(Model model) {

        model.addAttribute("jogadores", jogadorService.listarTodos());

        return "jogador/lista";
    }

    @GetMapping("/{id}")
    public String detalhes(@PathVariable Long id, Model model) {

        model.addAttribute("jogador", jogadorService.buscarPorId(id));

        return "jogador/detalhes";
    }

    @GetMapping("/selecao/{idSelecao}")
    public String listarPorSelecao(
            @PathVariable Long idSelecao,
            Model model) {

        model.addAttribute("jogadores",
                jogadorService.buscarPorSelecaoId(idSelecao));

        model.addAttribute("selecao",
                selecaoService.buscarPorId(idSelecao));

        return "jogador/lista";
    }

    @GetMapping("/buscar")
    public String buscarPorPosicao(
            @RequestParam String posicao,
            Model model) {

        model.addAttribute("jogadores",
                jogadorService.buscarPorPosicao(posicao));
        model.addAttribute("filtroPosicao", posicao);

        return "jogador/lista";
    }
}