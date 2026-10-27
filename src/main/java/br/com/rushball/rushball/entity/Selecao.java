package br.com.rushball.rushball.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Selecao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSelecao;
    
    @NotBlank(message = "O nome da seleção é obrigatório.")
    @Size(max = 100 ,message="O nome deve ter no máximo 100 caracteres.")
    @Column(nullable = false, length = 100)
    private String nomeSelecao;
    
    @NotBlank(message = "O nome da seleção é obrigatório.")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
    @Column(nullable = false, length = 100)
    private String paisSelecao;
    
    @Size(max = 500, message = "O caminho da bandeira deve ter no máximo 500 caracteres.")
    @Column(length = 500)
    private String bandeiraSelecao;
    
    @Size(max = 10,message = "O grupo deve ter no máximo 10 caracteres.")
    @Column(length = 10)
    private String grupoSelecao;
}
