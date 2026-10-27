package br.com.rushball.rushball.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJogador;
    
    @NotBlank(message = "O nome do jogador é obrigatório.")
    @Size(max = 100,message = "O nome deve ter no máximo 100 caracteres.")
    @Column(nullable = false, length = 100)
    private String nomeJogador;
    
    @NotBlank(message = "A posição do jogador é obrigatória.")
    @Size (max = 50,message = "A posição do jogador é obrigatória.")
    @Column(nullable = false, length = 50)
    private String posicaoJogador;
    
    @Min(value = 1, message = "O número da camisa deve ser no mínimo 1.")
    @Max(value = 99,message = "O número da camisa deve ser no máximo 99.")
    @Column
    private Integer numeroCamisaJogador;
}
