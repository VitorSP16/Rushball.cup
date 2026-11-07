package br.com.rushball.rushball.repository;

import br.com.rushball.rushball.entity.EstatisticaJogador;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EstatisticaJogadorRepository extends JpaRepository<EstatisticaJogador, Long> {
   
    List<EstatisticaJogador> findByJogadorIdJogador(Long idJogador);

    List<EstatisticaJogador> findByJogoIdJogo(Long idJogo);
}