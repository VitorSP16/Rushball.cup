package br.com.rushball.rushball.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import br.com.rushball.rushball.entity.Jogador;
import br.com.rushball.rushball.entity.Selecao;
import br.com.rushball.rushball.entity.Usuario;
import br.com.rushball.rushball.repository.JogadorRepository;
import br.com.rushball.rushball.repository.SelecaoRepository;
import br.com.rushball.rushball.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final JogadorRepository jogadorRepository;
    private final SelecaoRepository selecaoRepository;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            JogadorRepository jogadorRepository,
            SelecaoRepository selecaoRepository) {

        this.usuarioRepository = usuarioRepository;
        this.jogadorRepository = jogadorRepository;
        this.selecaoRepository = selecaoRepository;
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

    public Usuario definirSelecaoFavorita(Long idUsuario, Long idSelecao) {

        Usuario usuario = buscarPorId(idUsuario);

        Selecao selecao = selecaoRepository.findById(idSelecao)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Seleção não encontrada: " + idSelecao));

        usuario.setSelecaoFavorita(selecao);

        return usuarioRepository.save(usuario);
    }

    public Usuario removerSelecaoFavorita(Long idUsuario) {

        Usuario usuario = buscarPorId(idUsuario);

        usuario.setSelecaoFavorita(null);

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario adicionarJogadorFavorito(
            Long idUsuario,
            Long idJogador) {

        Usuario usuario = buscarPorId(idUsuario);

        Jogador jogador = jogadorRepository.findById(idJogador)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Jogador não encontrado: " + idJogador));

        if (!usuario.getJogadoresFavoritos().contains(jogador)) {
            usuario.getJogadoresFavoritos().add(jogador);
        }

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario removerJogadorFavorito(
            Long idUsuario,
            Long idJogador) {

        Usuario usuario = buscarPorId(idUsuario);

        Jogador jogador = jogadorRepository.findById(idJogador)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Jogador não encontrado: " + idJogador));

        usuario.getJogadoresFavoritos().remove(jogador);

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void excluir(Long id) {

        Usuario usuario = buscarPorId(id);

        usuarioRepository.delete(usuario);
    }
}