package br.com.rushball.rushball.entity;

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
public class Selecao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSelecao;

    private String nomeSelecao;
    private String paisSelecao;
    private String bandeiraSelecao;
    private String grupoSelecao;

}
