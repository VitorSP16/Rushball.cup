package br.com.rushball.rushball.service;

import java.util.List;


import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import br.com.rushball.rushball.entity.Noticia;
import br.com.rushball.rushball.repository.NoticiaRepository;

@Service
public class NoticiaService {

    private final NoticiaRepository noticiaRepository;

    public NoticiaService(NoticiaRepository noticiaRepository) {
        this.noticiaRepository = noticiaRepository;
    }

    public List<Noticia> listarTodas() {
        return noticiaRepository.findAll();
    }

    public Noticia buscarPorId(Long id) {
        return noticiaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Notícia não encontrada: " + id));
    }

    public List<Noticia> buscarPorCategoria(String categoria) {

    if (categoria == null || categoria.isBlank()) {
        throw new IllegalArgumentException(
                "A categoria deve ser informada.");
    }

    return noticiaRepository.findByCategoriaNoticiaIgnoreCase(
            categoria.trim()
    );
}

    public List<Noticia> listarDestaques() {
        return noticiaRepository.findByDestaqueNoticiaTrue();
    }

    public List<Noticia> buscarPorSelecao(Long idSelecao) {
        return noticiaRepository.findBySelecaoIdSelecao(idSelecao);
    }

    public List<Noticia> buscarPorJogador(Long idJogador) {
        return noticiaRepository.findByJogadorIdJogador(idJogador);
    }

    public List<Noticia> buscarPorJogo(Long idJogo) {
        return noticiaRepository.findByJogoIdJogo(idJogo);
    }

        
}