
package br.com.rushball.rushball.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class EstatisticaJogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstatistica;

    @Min(value = 0, message = "A quantidade de gols não pode ser negativa.")
    @Column(nullable = false)
    private int gols;

    @Min(value = 0, message = "A quantidade de assistências não pode ser negativa.")
    @Column(nullable = false)
    private int assistencias;

    @Min(value = 0, message = "A quantidade de cartões amarelos não pode ser negativa.")
    @Column(nullable = false)
    private int cartoesAmarelos;

    @Min(value = 0, message = "A quantidade de cartões vermelhos não pode ser negativa.")
    @Column(nullable = false)
    private int cartoesVermelhos;

    @Min(value = 0, message = "Os minutos jogados não podem ser negativos.")
    @Column(nullable = false)
    private int minutosJogados;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_jogador", nullable = false)
       private Jogador jogador;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_jogo", nullable = false)
       private Jogo jogo;
}
