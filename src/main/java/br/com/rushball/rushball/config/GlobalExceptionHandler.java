
package br.com.rushball.rushball.config;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public String tratarNaoEncontrado(
            EntityNotFoundException erro,
            Model model) {

        model.addAttribute("erro", erro.getMessage());

        return "erro";
    }


@ExceptionHandler(IllegalArgumentException.class)
public String tratarArgumentoInvalido(
        IllegalArgumentException erro,
        Model model) {

    model.addAttribute("erro", erro.getMessage());

    return "erro";
}
   
@ExceptionHandler(AccessDeniedException.class)
public String tratarAcessoNegado(
        AccessDeniedException erro,
        Model model) {

    model.addAttribute("erro",
            "Você não tem permissão para acessar esta página.");

    return "erro";
   }

   @ExceptionHandler(Exception.class)
public String tratarErroGeral(
        Exception erro,
        Model model) {

    model.addAttribute("erro",
            "Ocorreu um erro inesperado. Tente novamente mais tarde.");

    return "erro";
}
}
