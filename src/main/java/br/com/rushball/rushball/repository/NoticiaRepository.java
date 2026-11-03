package br.com.rushball.rushball.repository;

import br.com.rushball.rushball.entity.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticiaRepository extends JpaRepository<Noticia, Long> {

}