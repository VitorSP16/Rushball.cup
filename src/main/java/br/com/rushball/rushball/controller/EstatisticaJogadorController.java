package br.com.rushball.rushball.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.rushball.rushball.service.EstatisticaJogadorService;
import br.com.rushball.rushball.service.JogadorService;
import br.com.rushball.rushball.service.JogoService;

@Controller
@RequestMapping("/estatisticas")
public class EstatisticaJogadorController {

    private final EstatisticaJogadorService estatisticaService;
    private final JogadorService jogadorService;
    private final JogoService jogoService;

    public EstatisticaJogadorController(
            EstatisticaJogadorService estatisticaService,
            JogadorService jogadorService,
            JogoService jogoService) {

        this.estatisticaService = estatisticaService;
        this.jogadorService = jogadorService;
        this.jogoService = jogoService;
    }

    @GetMapping
    public String listar(Model model) {

        model.addAttribute("estatisticas",
                estatisticaService.listarTodas());

        return "estatistica/lista";
    }

    @GetMapping("/{id}")
    public String detalhes(@PathVariable Long id, Model model) {

        model.addAttribute("estatistica",
                estatisticaService.buscarPorId(id));

        return "estatistica/detalhes";
    }

    @GetMapping("/jogador/{idJogador}")
    public String buscarPorJogador(
            @PathVariable Long idJogador,
            Model model) {

        model.addAttribute("jogador",
                jogadorService.buscarPorId(idJogador));

        model.addAttribute("estatisticas",
                estatisticaService.buscarPorJogadorId(idJogador));

        return "estatistica/lista";
    }

    @GetMapping("/jogo/{idJogo}")
    public String buscarPorJogo(
            @PathVariable Long idJogo,
            Model model) {

        model.addAttribute("jogo",
                jogoService.buscarPorId(idJogo));

        model.addAttribute("estatisticas",
                estatisticaService.buscarPorJogoId(idJogo));

        return "estatistica/lista";
    }
}