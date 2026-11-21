package br.com.rushball.rushball.repository;

import br.com.rushball.rushball.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmailUsuarioIgnoreCase(String emailUsuario);
    Optional<Usuario> findByEmailUsuarioIgnoreCase(String emailUsuario);
}