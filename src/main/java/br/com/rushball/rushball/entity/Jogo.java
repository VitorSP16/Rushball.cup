package br.com.rushball.rushball.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Jogo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJogo;

    private LocalDateTime dataHoraJogo;

    private String faseJogo;

    private Integer placarCasaJogo;
    private Integer placarVistitanteJogo;
    
}
