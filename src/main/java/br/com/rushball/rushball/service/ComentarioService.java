package br.com.rushball.rushball.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import br.com.rushball.rushball.entity.Comentario;
import br.com.rushball.rushball.repository.ComentarioRepository;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;

    public ComentarioService(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    public List<Comentario> listarTodos() {
        return comentarioRepository.findAll();
    }

    public Comentario buscarPorId(Long id) {
        return comentarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Comentário não encontrado: " + id));
    }

    public Comentario salvar(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    public void excluir(Long id) {
        if (!comentarioRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Comentário não encontrado: " + id);
        }

        comentarioRepository.deleteById(id);
    }
}