
package br.com.rushball.rushball.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNoticia;

    @NotBlank(message = "O título da notícia é obrigatório.")
    @Size(max = 150, message = "O título deve ter no máximo 150 caracteres.")
    @Column(nullable = false, length = 150)
    private String tituloNoticia;

    @Size(max = 500, message = "O resumo deve ter no máximo 500 caracteres.")
    @Column(length = 500)
    private String resumoNoticia;

    @NotBlank(message = "O conteúdo da notícia é obrigatório.")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String conteudoNoticia;

    @NotNull(message = "A data de publicação é obrigatória.")
    @Column(nullable = false)
    private LocalDateTime dataPublicacaoNoticia;

    @NotBlank(message = "O autor da notícia é obrigatório.")
    @Size(max = 100, message = "O autor deve ter no máximo 100 caracteres.")
    @Column(nullable = false, length = 100)
    private String autorNoticia;

    @Size(max = 500, message = "O caminho da imagem deve ter no máximo 500 caracteres.")
    @Column(length = 500)
    private String imagemCapaNoticia;

    @Size(max = 50, message = "A categoria deve ter no máximo 50 caracteres.")
    @Column(length = 50)
    private String categoriaNoticia;

    @Column(nullable = false)
    private boolean destaqueNoticia;

    @OneToMany(mappedBy = "noticia")
      private List<Comentario> comentarios = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jogador", nullable = true)
      private Jogador jogador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_selecao", nullable = true)
       private Selecao selecao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jogo", nullable = true)
       private Jogo jogo;
}
