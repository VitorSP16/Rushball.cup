package br.com.rushball.rushball.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import br.com.rushball.rushball.entity.EstatisticaJogador;
import br.com.rushball.rushball.repository.EstatisticaJogadorRepository;

@Service
public class EstatisticaJogadorService {

    private final EstatisticaJogadorRepository estatisticaRepository;

    public EstatisticaJogadorService(
            EstatisticaJogadorRepository estatisticaRepository) {
        this.estatisticaRepository = estatisticaRepository;
    }

    public List<EstatisticaJogador> listarTodas() {
        return estatisticaRepository.findAll();
    }

    public EstatisticaJogador buscarPorId(Long id) {
        return estatisticaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Estatística não encontrada: " + id));
    }

    public List<EstatisticaJogador> buscarPorJogadorId(Long idJogador) {
        return estatisticaRepository.findByJogadorIdJogador(idJogador);
    }

    public List<EstatisticaJogador> buscarPorJogoId(Long idJogo) {
        return estatisticaRepository.findByJogoIdJogo(idJogo);
    }

    public EstatisticaJogador salvar(EstatisticaJogador estatistica) {
        return estatisticaRepository.save(estatistica);
    }

    public void excluir(Long id) {
        if (!estatisticaRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Estatística não encontrada: " + id);
        }

        estatisticaRepository.deleteById(id);
    }
}