package br.com.rushball.rushball.repository;

import br.com.rushball.rushball.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}