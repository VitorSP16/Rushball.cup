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

    public List<Jogo> buscarPorSelecaoCasa(Long idSelecao) {
        return jogoRepository.findBySelecaoCasaIdSelecao(idSelecao);
    }

    public List<Jogo> buscarPorSelecaoVisitante(Long idSelecao) {
        return jogoRepository.findBySelecaoVisitanteIdSelecao(idSelecao);
    }

    public List<Jogo> buscarPorFase(String fase) {
        return jogoRepository.findByFaseJogo(fase);
    }

    public List<Jogo> buscarPorStatus(String status) {
        return jogoRepository.findByStatusJogo(status);
    }

}