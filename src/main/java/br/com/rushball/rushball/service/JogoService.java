package br.com.rushball.rushball.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import br.com.rushball.rushball.entity.Jogo;
import br.com.rushball.rushball.repository.JogoRepository;

@Service
public class JogoService {

    private final JogoRepository jogoRepository;

    public JogoService(JogoRepository jogoRepository) {
        this.jogoRepository = jogoRepository;
    }

    public List<Jogo> listarTodos() {
        return jogoRepository.findAll();
    }

    public Jogo buscarPorId(Long id) {
        return jogoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Jogo não encontrado: " + id));
    }

    public Jogo salvar(Jogo jogo) {
        return jogoRepository.save(jogo);
    }

    public void excluir(Long id) {
    if (!jogoRepository.existsById(id)) {
        throw new EntityNotFoundException(
                "Jogo não encontrado: " + id);
    }

    try {
        jogoRepository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
        throw new IllegalStateException(
                "Não é possível excluir este jogo porque existem notícias ou estatísticas vinculadas a ele.", e);
    }
}
}