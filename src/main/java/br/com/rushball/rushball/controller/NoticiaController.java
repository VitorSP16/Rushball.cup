package br.com.rushball.rushball.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.rushball.rushball.service.JogadorService;
import br.com.rushball.rushball.service.JogoService;
import br.com.rushball.rushball.service.NoticiaService;
import br.com.rushball.rushball.service.SelecaoService;

@Controller
@RequestMapping("/noticias")
public class NoticiaController {

    private final NoticiaService noticiaService;
    private final JogadorService jogadorService;
    private final SelecaoService selecaoService;
    private final JogoService jogoService;

    public NoticiaController(
            NoticiaService noticiaService,
            JogadorService jogadorService,
            SelecaoService selecaoService,
            JogoService jogoService) {

        this.noticiaService = noticiaService;
        this.jogadorService = jogadorService;
        this.selecaoService = selecaoService;
        this.jogoService = jogoService;
    }

    @GetMapping
    public String listar(Model model) {

        model.addAttribute("noticias",
                noticiaService.listarTodas());

        return "noticia/lista";
    }

    @GetMapping("/{id}")
    public String detalhes(
            @PathVariable Long id,
            Model model) {

        model.addAttribute("noticia",
                noticiaService.buscarPorId(id));

        return "noticia/detalhes";
    }

    @GetMapping("/destaques")
    public String listarDestaques(Model model) {

        model.addAttribute("noticias",
                noticiaService.listarDestaques());

        return "noticia/lista";
    }

    @GetMapping("/categoria")
    public String buscarPorCategoria(
            @RequestParam String categoria,
            Model model) {

        model.addAttribute("noticias",
                noticiaService.buscarPorCategoria(categoria));

        model.addAttribute("filtroCategoria", categoria);

        return "noticia/lista";
    }

    @GetMapping("/jogador/{idJogador}")
    public String buscarPorJogador(
            @PathVariable Long idJogador,
            Model model) {

        model.addAttribute("noticias",
                noticiaService.buscarPorJogador(idJogador));

        model.addAttribute("jogador",
                jogadorService.buscarPorId(idJogador));

        return "noticia/lista";
    }

    @GetMapping("/selecao/{idSelecao}")
    public String buscarPorSelecao(
            @PathVariable Long idSelecao,
            Model model) {

        model.addAttribute("noticias",
                noticiaService.buscarPorSelecao(idSelecao));

        model.addAttribute("selecao",
                selecaoService.buscarPorId(idSelecao));

        return "noticia/lista";
    }

    @GetMapping("/jogo/{idJogo}")
    public String buscarPorJogo(
            @PathVariable Long idJogo,
            Model model) {

        model.addAttribute("noticias",
                noticiaService.buscarPorJogo(idJogo));

        model.addAttribute("jogo",
                jogoService.buscarPorId(idJogo));

        return "noticia/lista";
    }
}