package br.com.rushball.rushball.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.rushball.rushball.service.JogoService;
import br.com.rushball.rushball.service.SelecaoService;

@Controller
@RequestMapping("/jogos")
public class JogoController {

    private final JogoService jogoService;
    private final SelecaoService selecaoService;

    public JogoController(
            JogoService jogoService,
            SelecaoService selecaoService) {

        this.jogoService = jogoService;
        this.selecaoService = selecaoService;
    }

    @GetMapping
    public String listar(Model model) {

        model.addAttribute("jogos", jogoService.listarTodos());

        return "jogo/lista";
    }

    @GetMapping("/{id}")
    public String detalhes(@PathVariable Long id, Model model) {

        model.addAttribute("jogo", jogoService.buscarPorId(id));

        return "jogo/detalhes";
    }

    @GetMapping("/buscar/fase")
    public String buscarPorFase(
            @RequestParam String fase,
            Model model) {

        model.addAttribute("jogos", jogoService.buscarPorFase(fase));

        model.addAttribute("filtroFase", fase);

        return "jogo/lista";
    }

    @GetMapping("/buscar/status")
    public String buscarPorStatus(
            @RequestParam String status,
            Model model) {

        model.addAttribute("jogos", jogoService.buscarPorStatus(status));

        model.addAttribute("filtroStatus", status);

        return "jogo/lista";
    }

    @GetMapping("/selecao/{idSelecao}/casa")
    public String buscarPorSelecaoCasa(
            @PathVariable Long idSelecao,
            Model model) {

        model.addAttribute("jogos",
                jogoService.buscarPorSelecaoCasa(idSelecao));

        model.addAttribute("selecao",
                selecaoService.buscarPorId(idSelecao));

        return "jogo/lista";
    }

    @GetMapping("/selecao/{idSelecao}/visitante")
    public String buscarPorSelecaoVisitante(
            @PathVariable Long idSelecao,
            Model model) {

        model.addAttribute("jogos",
                jogoService.buscarPorSelecaoVisitante(idSelecao));

        model.addAttribute("selecao",
                selecaoService.buscarPorId(idSelecao));

        return "jogo/lista";
    }
}