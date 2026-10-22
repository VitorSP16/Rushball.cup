package br.com.rushball.rushball.entity;

import java.time.LocalDateTime;

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
public class Noticia {
   
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNoticia;

    @Column(nullable = false, length = 150)
    private String tiuloNoticia;

    @Column(length = 500)
    private String resumoNoticia;

    @Column(nullable = false , columnDefinition = "TEXT")
    private String conteudoNoticia;

    @Column(nullable = false)
    private LocalDateTime  dataPublicacaoNoticia;

    @Column(nullable = false, length = 100)
    private String autorNoticia;
    
    @Column(length = 500)
    private  String imagem_capaNoticia;

    @Column(length = 50)
    private String categoriaNoticia;

    @Column(nullable = false)
    private boolean destaqueNoticia;

}
