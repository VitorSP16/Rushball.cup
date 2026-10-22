
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
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(nullable = false, length = 100)
    private String nomeUsuario;

    @Column(nullable = false, unique = true, length = 150)
    private String emailUsuario;

    @Column(nullable = false, length = 255)
    private String senhaUsuario;

    @Column(nullable = false, length = 30)
    private String papelUsuario;
}
