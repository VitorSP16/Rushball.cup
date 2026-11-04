package br.com.rushball.rushball.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import br.com.rushball.rushball.entity.Usuario;
import br.com.rushball.rushball.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Usuário não encontrado: " + id));
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    @Transactional
    public void excluir(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Usuário não encontrado: " + id);
        }

        try {
            usuarioRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException(
                    "Não é possível excluir este usuário porque existem comentários vinculados a ele.", e);
        }
    }
}