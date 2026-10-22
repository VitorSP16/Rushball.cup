package br.com.rushball.rushball.entity;

import jakarta.persistence.Column;
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
public class EstatisticaJogador {
  
    @Id 
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long idEstatistica;

    @Column(nullable = false)
    private  int golEstatistica;

    @Column(nullable = false)
    private int assistenciaEstatistica;

    @Column(nullable = false)
    private int cartao_amareloEstatistica;

    @Column(nullable = false)
    private int cartao_vermelhoEstatistica;

    @Column(nullable = false)
    private int minutos_jogadoEstatistica;
}
