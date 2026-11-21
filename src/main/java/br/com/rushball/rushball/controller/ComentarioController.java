
package br.com.rushball.rushball.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import br.com.rushball.rushball.Dto.ComentarioDto;
import br.com.rushball.rushball.service.ComentarioService;

import br.com.rushball.rushball.service.NoticiaService;

@Controller
@RequestMapping("/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioService;
    private final NoticiaService noticiaService;

    public ComentarioController(
            ComentarioService comentarioService,
            NoticiaService noticiaService) {

        this.comentarioService = comentarioService;
        this.noticiaService = noticiaService;
    }
    

    @PostMapping("/publicar/{idNoticia}")
         public String publicar(
         @PathVariable Long idNoticia,
         @Valid @ModelAttribute("comentarioDto") ComentarioDto dto,
         BindingResult resultado,
         Authentication authentication,
         Model model) {

    if (resultado.hasErrors()) {

        model.addAttribute("noticia",
                noticiaService.buscarPorId(idNoticia));

        model.addAttribute("comentarios",
                comentarioService.buscarPorNoticiaId(idNoticia));

        return "comentario/lista";
    }

    comentarioService.publicar(
            dto,
            authentication.getName(),
            idNoticia
    );

    return "redirect:/noticias/" + idNoticia;
}

    @GetMapping("/noticia/{idNoticia}")
    public String listarPorNoticia(
            @PathVariable Long idNoticia,
            Model model) {

        model.addAttribute("noticia",
                noticiaService.buscarPorId(idNoticia));

        model.addAttribute("comentarios",
                comentarioService.buscarPorNoticiaId(idNoticia));

        model.addAttribute("comentarioDto",
                new ComentarioDto());

        return "comentario/lista";
    }
    @PostMapping("/excluir/{idComentario}")
        public String excluir( 
        @PathVariable Long idComentario,
        Authentication authentication,
        Model model) {

       try {
        Long idNoticia = comentarioService.excluir(
                idComentario,
                authentication.getName()
        );

        return "redirect:/noticias/" + idNoticia;

      } catch (EntityNotFoundException e) {
        model.addAttribute("erro", e.getMessage());
        return "erro";
    }
   }
}
