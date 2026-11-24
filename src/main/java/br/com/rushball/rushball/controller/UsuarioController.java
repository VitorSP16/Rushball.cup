package br.com.rushball.rushball.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.rushball.rushball.Dto.UsuarioCadastroDto;
import br.com.rushball.rushball.entity.Usuario;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

import br.com.rushball.rushball.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastrar")
        public String cadastrar(
        @Valid @ModelAttribute UsuarioCadastroDto dto,
        BindingResult resultado,Model model) {

       if (resultado.hasErrors()) {
        return "usuario/cadastro";
      }

      try {
        usuarioService.cadastrar(dto);
    } catch (IllegalArgumentException e) {
        model.addAttribute("erro", e.getMessage());
        return "usuario/cadastro";
    }

      return "redirect:/login";
    }

    @GetMapping("/cadastrar")
    public String exibirCadastro(Model model) {
    model.addAttribute("usuarioCadastroDto",
            new UsuarioCadastroDto());

    return "usuario/cadastro";
}

    @GetMapping("/listar")
    public String listar(Model model) {

        model.addAttribute("usuarios",
                usuarioService.listarTodos());

        return "usuario/lista";
    }

    @GetMapping("/perfil")
    public String perfil(Authentication authentication, Model model) {
    
        String emailUsuario = authentication.getName();
    
        Usuario usuario = usuarioService.buscarPorEmail(emailUsuario);
    
        model.addAttribute("usuario", usuario);
    
        model.addAttribute("selecaoFavorita",
                usuario.getSelecaoFavorita());
    
        model.addAttribute("jogadoresFavoritos",
                usuario.getJogadoresFavoritos());
    
        return "usuario/perfil";
    }

    @PostMapping("/favoritos/selecao/{idSelecao}")
    public String adicionarSelecaoFavorita(
        @PathVariable Long idSelecao,
        Authentication authentication) {

    String emailUsuario = authentication.getName();

    usuarioService.definirSelecaoFavorita(
            emailUsuario, idSelecao);

    return "redirect:/usuarios/perfil";
     }

     @PostMapping("/favoritos/jogador/{idJogador}")
public String adicionarJogadorFavorito(
        @PathVariable Long idJogador,
        Authentication authentication) {

    String emailUsuario = authentication.getName();

    usuarioService.adicionarJogadorFavorito(
            emailUsuario, idJogador);

    return "redirect:/usuarios/perfil";
}

     @PostMapping("/favoritos/selecao/remover")
public String removerSelecaoFavorita(
        Authentication authentication) {

    String emailUsuario = authentication.getName();

    usuarioService.removerSelecaoFavorita(emailUsuario);

    return "redirect:/usuarios/perfil";
}

@PostMapping("/favoritos/jogador/remover/{idJogador}")
public String removerJogadorFavorito(
        @PathVariable Long idJogador,
        Authentication authentication) {

    String emailUsuario = authentication.getName();

    usuarioService.removerJogadorFavorito(
            emailUsuario, idJogador);

    return "redirect:/usuarios/perfil";
}

    @GetMapping("/detalhes/{id}")
    public String detalhes(
            @PathVariable Long id,
            Model model) {

        model.addAttribute("usuario",
                usuarioService.buscarPorId(id));

        return "usuario/detalhes";
    }
}