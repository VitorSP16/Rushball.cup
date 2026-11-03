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
import jakarta.validation.constraints.Min;
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
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJogo;

    @NotNull(message = "A data e hora do jogo são obrigatórias.")
    @Column(nullable = false)
    private LocalDateTime dataHoraJogo;

    @NotBlank(message = "A fase do jogo é obrigatória.")
    @Size(max = 50, message = "A fase deve ter no máximo 50 caracteres.")
    @Column(nullable = false, length = 50)
    private String faseJogo;

    @Min(value = 0, message = "O placar não pode ser negativo.")
    private Integer placarCasaJogo;

    @Min(value = 0, message = "O placar não pode ser negativo.")
    private Integer placarVisitanteJogo;

    @NotBlank(message = "O status do jogo é obrigatório.")
    @Size(max = 30, message = "O status deve ter no máximo 30 caracteres.")
    @Column(nullable = false, length = 30)
    private String statusJogo;

    @OneToMany(mappedBy = "jogo")
       private List<Noticia> noticias = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_selecao_casa", nullable = false)
      private Selecao selecaoCasa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_selecao_visitante", nullable = false)
      private Selecao selecaoVisitante;

      @OneToMany(mappedBy = "jogo")
        private List<EstatisticaJogador> estatisticasJogadores = new ArrayList<>();
}