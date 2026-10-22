package br.com.rushball.rushball.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(nullable = false, length = 100)
    private String nomeJogador;

    @Column(nullable = false, length = 50)
    private String posicaoJogador;

    @Column
    private Integer numeroCamisaJogador;
}
