package br.com.rushball.rushball.repository;

import br.com.rushball.rushball.entity.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {

}