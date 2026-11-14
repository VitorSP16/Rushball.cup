package br.com.rushball.rushball.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import br.com.rushball.rushball.entity.Jogador;
import br.com.rushball.rushball.repository.JogadorRepository;

@Service
public class JogadorService {

    private final JogadorRepository jogadorRepository;

    public JogadorService(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
    }

    public List<Jogador> listarTodos() {
        return jogadorRepository.findAll();
    }

    public Jogador buscarPorId(Long id) {
        return jogadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Jogador não encontrado: " + id));
    }
    public List<Jogador> buscarPorSelecaoId(Long idSelecao) {
        return jogadorRepository.findBySelecaoIdSelecao(idSelecao);
    }

    public List<Jogador> buscarPorPosicao(String posicao) {
        return jogadorRepository.findByPosicaoJogadorContainingIgnoreCase(posicao);
    }

    public Jogador salvar(Jogador jogador) {
        return jogadorRepository.save(jogador);
    }

    public void excluir(Long id) {
        if (!jogadorRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Jogador não encontrado: " + id);
        }

        jogadorRepository.deleteById(id);
    }
}