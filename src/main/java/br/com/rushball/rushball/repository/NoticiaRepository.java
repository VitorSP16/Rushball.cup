package br.com.rushball.rushball.repository;

import br.com.rushball.rushball.entity.Noticia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
  
    List<Noticia> findByCategoriaNoticia(String categoriaNoticia);

    List<Noticia> findByDestaqueNoticiaTrue();

    List<Noticia> findBySelecaoIdSelecao(Long idSelecao);

    List<Noticia> findByJogadorIdJogador(Long idJogador);

    List<Noticia> findByJogoIdJogo(Long idJogo);

}