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
public class Selecao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSelecao;

    @Column(nullable = false, length = 100)
    private String nomeSelecao;

    @Column(nullable = false, length = 100)
    private String paisSelecao;

    @Column(length = 500)
    private String bandeiraSelecao;

    @Column(length = 10)
    private String grupoSelecao;
}
