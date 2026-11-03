package br.com.rushball.rushball.repository;

import br.com.rushball.rushball.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}