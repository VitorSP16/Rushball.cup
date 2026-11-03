package br.com.rushball.rushball.repository;

import br.com.rushball.rushball.entity.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogoRepository extends JpaRepository<Jogo, Long> {

}