package br.com.rushball.rushball.repository;

import br.com.rushball.rushball.entity.Comentario;
import br.com.rushball.rushball.entity.Noticia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
  
    List<Comentario> findByUsuarioIdUsuario(Long idUsuario);

    List<Comentario> findByNoticiaIdNoticia(Long idNoticia);
}