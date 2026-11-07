package br.com.rushball.rushball.repository;

import br.com.rushball.rushball.entity.Jogo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

public interface JogoRepository extends JpaRepository<Jogo, Long> {

     List<Jogo> findBySelecaoCasaIdSelecao(Long idSelecao);

    List<Jogo> findBySelecaoVisitanteIdSelecao(Long idSelecao);

    List<Jogo> findByFaseJogo(String faseJogo);

    List<Jogo> findByStatusJogo(String statusJogo);

}