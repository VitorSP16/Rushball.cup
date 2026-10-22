package br.com.rushball.rushball.entity;

import java.time.LocalDateTime;

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
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJogo;

    @Column(nullable = false)
    private LocalDateTime dataHoraJogo;

    @Column(nullable = false, length = 50)
    private String faseJogo;

    @Column
    private Integer placarCasaJogo;

    @Column
    private Integer placarVisitanteJogo;

    @Column(nullable = false, length = 30)
    private String statusJogo;
}
