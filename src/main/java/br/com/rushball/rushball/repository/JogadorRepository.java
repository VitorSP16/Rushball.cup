package br.com.rushball.rushball.repository;

import br.com.rushball.rushball.entity.Jogador;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {

    List<Jogador> findBySelecaoIdSelecao(Long idSelecao);

    List<Jogador> findByPosicaoJogadorContainingIgnoreCase(String posicaoJogador);

}