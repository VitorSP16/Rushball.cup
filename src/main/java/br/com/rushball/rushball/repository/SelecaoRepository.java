package br.com.rushball.rushball.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rushball.rushball.entity.Selecao;

public interface SelecaoRepository extends JpaRepository<Selecao, Long> {
  List<Selecao> findByNomeSelecaoContainingIgnoreCase(String nomeSelecao);
}