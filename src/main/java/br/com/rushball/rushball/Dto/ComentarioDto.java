
package br.com.rushball.rushball.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioDto {

    @NotBlank(message = "O comentário não pode ficar vazio")
    @Size(max = 5000, message = "O comentário deve ter no máximo 5000 caracteres")
    private String textoComentario;
}
