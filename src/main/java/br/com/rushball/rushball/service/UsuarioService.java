package br.com.rushball.rushball.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import br.com.rushball.rushball.Dto.UsuarioCadastroDto;
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
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            JogadorRepository jogadorRepository,
            SelecaoRepository selecaoRepository,
            PasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.jogadorRepository = jogadorRepository;
        this.selecaoRepository = selecaoRepository;
        this.passwordEncoder= passwordEncoder;
    }

    public Usuario cadastrar(UsuarioCadastroDto dto) {
        
        if (dto == null) {
    throw new IllegalArgumentException(
            "Os dados do cadastro são obrigatórios.");
}

        if (dto.getSenhaUsuario() == null ||
        !dto.getSenhaUsuario().equals(dto.getConfirmacaoSenha())) {
    throw new IllegalArgumentException("As senhas não coincidem.");
}
    
        if (usuarioRepository.existsByEmailUsuarioIgnoreCase(dto.getEmailUsuario().trim())) {
            throw new IllegalArgumentException("Este email já está cadastrado.");
        }
       
        Usuario usuario = new Usuario();
        
        usuario.setNomeUsuario(dto.getNomeUsuario().trim());
        usuario.setEmailUsuario(dto.getEmailUsuario().trim());
        
        String senhaHash = passwordEncoder.encode(dto.getSenhaUsuario());
        usuario.setSenhaUsuario(senhaHash);
    
        usuario.setPapelUsuario("USER");
    
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Usuário não encontrado: " + id));
    }

    

    public Usuario buscarPorEmail(String emailUsuario) {

        return usuarioRepository
                .findByEmailUsuarioIgnoreCase(emailUsuario)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Usuário não encontrado."));
    }

    public Usuario definirSelecaoFavorita(String emailUsuario, Long idSelecao) {

        Usuario usuario = usuarioRepository
                .findByEmailUsuarioIgnoreCase(emailUsuario)
                .orElseThrow(() -> new EntityNotFoundException(
                    "Usuário não encontrado."));

        Selecao selecao = selecaoRepository.findById(idSelecao)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Seleção não encontrada: " + idSelecao));

        usuario.setSelecaoFavorita(selecao);

        return usuarioRepository.save(usuario);
    }

    public Usuario removerSelecaoFavorita(String emailUsuario) {

        Usuario usuario = usuarioRepository
                .findByEmailUsuarioIgnoreCase(emailUsuario)
                .orElseThrow(() -> new EntityNotFoundException(
                    "Usuário não encontrado."));

        usuario.setSelecaoFavorita(null);

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario adicionarJogadorFavorito(
            String emailUsuario,
            Long idJogador) {
         
                Usuario usuario = usuarioRepository
                .findByEmailUsuarioIgnoreCase(emailUsuario)
                .orElseThrow(() -> new EntityNotFoundException(
                    "Usuário não encontrado."));
        
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
            String emailUsuario,
            Long idJogador) {

                Usuario usuario = usuarioRepository
                .findByEmailUsuarioIgnoreCase(emailUsuario)
                .orElseThrow(() -> new EntityNotFoundException(
                    "Usuário não encontrado."));

        Jogador jogador = jogadorRepository.findById(idJogador)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Jogador não encontrado: " + idJogador));

        usuario.getJogadoresFavoritos().remove(jogador);

        return usuarioRepository.save(usuario);
    }

   @Transactional
   public void excluirPropriaConta(String emailUsuario) {
    Usuario usuario = buscarPorEmail(emailUsuario);
    usuarioRepository.delete(usuario);
   }

  @Transactional
   public void excluirUsuario(Long id) {
    Usuario usuario = buscarPorId(id);
    usuarioRepository.delete(usuario);
   }
}