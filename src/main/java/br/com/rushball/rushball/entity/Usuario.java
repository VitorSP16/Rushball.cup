
package br.com.rushball.rushball.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @NotBlank(message = "O nome do usuário é obrigatório.")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
    @Column(nullable = false, length = 100)
    private String nomeUsuario;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "Informe um e-mail válido.")
    @Size(max = 150, message = "O e-mail deve ter no máximo 150 caracteres.")
    @Column(nullable = false, unique = true, length = 150)
    private String emailUsuario;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 8, max = 255,
          message = "A senha deve ter pelo menos 8 caracteres.")
    @Column(nullable = false, length = 255)
    private String senhaUsuario;

    @NotBlank(message = "O papel do usuário é obrigatório.")
    @Size(max = 30, message = "O papel deve ter no máximo 30 caracteres.")
    @Column(nullable = false, length = 30)
    private String papelUsuario;

    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_selecao_favorita", nullable = true)
      private Selecao selecaoFavorita;

      @ManyToMany(fetch = FetchType.LAZY)
      @JoinTable( name = "jogador_favorito",
    joinColumns = @JoinColumn(name = "id_usuario", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "id_jogador", nullable = false)
   )
    private List<Jogador> jogadoresFavoritos = new ArrayList<>();
}
