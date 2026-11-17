package br.com.rushball.rushball.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioCadastroDto {

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nomeUsuario;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Digite um email válido")
    private String emailUsuario;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8, max = 72, message = "A senha deve ter entre 8 e 72 caracteres")
    private String senhaUsuario;

    @NotBlank(message = "A confirmação da senha é obrigatória")
    private String confirmacaoSenha;
}