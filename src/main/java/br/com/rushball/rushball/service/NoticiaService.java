package br.com.rushball.rushball.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
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
        return noticiaRepository.findByCategoriaNoticia(categoria);
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

    public Noticia salvar(Noticia noticia) {
        return noticiaRepository.save(noticia);
    }

    public void excluir(Long id) {
        if (!noticiaRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Notícia não encontrada: " + id);
        }

        try {
            noticiaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException(
                    "Não é possível excluir esta notícia porque existem comentários vinculados a ela.", e);
        }
    }
}