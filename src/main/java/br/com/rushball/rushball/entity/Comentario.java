package br.com.rushball.rushball.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Setter 
@Getter 
@NoArgsConstructor 
public class Comentario {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComentario;

    @NotBlank (message = "O comentário não pode ficar vazio.")
    @Size(max = 5000, message = "O comentário deve ter no máximo 5000 caracteres.")
    @Column(nullable = false , columnDefinition = "TEXT")
    private String textoComentario;

    @Column(nullable = false , updatable = false)
    private LocalDateTime dataCriacaoComentario;

    @PrePersist 
     protected void antesDeSalvar(){
        if (dataCriacaoComentario == null) {
            dataCriacaoComentario = LocalDateTime.now();
        }
     }
     
}
