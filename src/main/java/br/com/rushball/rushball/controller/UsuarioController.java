package br.com.rushball.rushball.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.rushball.rushball.Dto.UsuarioCadastroDto;

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

       usuarioService.cadastrar(dto);

      return "redirect:/login";
    }

    @GetMapping
    public String listar(Model model) {

        model.addAttribute("usuarios",
                usuarioService.listarTodos());

        return "usuario/lista";
    }

    @GetMapping("/{id}")
    public String detalhes(
            @PathVariable Long id,
            Model model) {

        model.addAttribute("usuario",
                usuarioService.buscarPorId(id));

        return "usuario/detalhes";
    }
}