package br.com.rushball.rushball.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import br.com.rushball.rushball.entity.Comentario;
import br.com.rushball.rushball.repository.ComentarioRepository;
import br.com.rushball.rushball.Dto.ComentarioDto;

import br.com.rushball.rushball.entity.Noticia;
import br.com.rushball.rushball.repository.NoticiaRepository;

import br.com.rushball.rushball.entity.Usuario;
import br.com.rushball.rushball.repository.UsuarioRepository;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final NoticiaRepository noticiaRepository;
    private final UsuarioRepository usuarioRepository;

    public ComentarioService(
         ComentarioRepository comentarioRepository, 
         NoticiaRepository noticiaRepository, 
         UsuarioRepository usuarioRepository) {

        this.comentarioRepository = comentarioRepository;
        this.noticiaRepository = noticiaRepository;
        this.usuarioRepository = usuarioRepository;
    }
     
   public Comentario publicar(
        ComentarioDto dto,
        String emailUsuario,
        Long idNoticia) {

    if (dto == null) {
        throw new IllegalArgumentException(
                "Os dados do comentário são obrigatórios.");
    }

    if (emailUsuario == null || emailUsuario.isBlank()) {
        throw new IllegalArgumentException(
                "O usuário não foi identificado.");
    }

    if (idNoticia == null) {
        throw new IllegalArgumentException(
                "A notícia é obrigatória.");
    }

    String texto = dto.getTextoComentario();

    if (texto == null || texto.isBlank()) {
        throw new IllegalArgumentException(
                "O comentário não pode ficar vazio.");
    }

    texto = texto.trim();

    if (texto.length() > 5000) {
        throw new IllegalArgumentException(
                "O comentário deve ter no máximo 5000 caracteres.");
    }

    Usuario usuario = usuarioRepository
            .findByEmailUsuarioIgnoreCase(emailUsuario)
            .orElseThrow(() -> new EntityNotFoundException(
                    "Usuário não encontrado."));

    Noticia noticia = noticiaRepository.findById(idNoticia)
            .orElseThrow(() -> new EntityNotFoundException(
                    "Notícia não encontrada: " + idNoticia));

    Comentario comentario = new Comentario();

    comentario.setTextoComentario(texto);
    comentario.setUsuario(usuario);
    comentario.setNoticia(noticia);

    return comentarioRepository.save(comentario);
  }

    public List<Comentario> listarTodos() {
        return comentarioRepository.findAll();
    }

    public Comentario buscarPorId(Long id) {
        return comentarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Comentário não encontrado: " + id));
    }

   public List<Comentario> buscarPorUsuarioId(Long idUsuario) {
        return comentarioRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public List<Comentario> buscarPorNoticiaId(Long idNoticia) {
        return comentarioRepository.findByNoticiaIdNoticia(idNoticia);
    }
    
    @Transactional
public Long excluir(Long idComentario, String emailUsuario) {

    Usuario usuario = usuarioRepository
            .findByEmailUsuarioIgnoreCase(emailUsuario)
            .orElseThrow(() -> new EntityNotFoundException(
                    "Usuário não encontrado."));

    Comentario comentario = buscarPorId(idComentario);

    boolean administrador =
            "ADMIN".equals(usuario.getPapelUsuario());

    boolean pertenceAoUsuario =
            comentario.getUsuario().getIdUsuario()
                    .equals(usuario.getIdUsuario());

    if (!administrador && !pertenceAoUsuario) {
        throw new EntityNotFoundException(
                "Comentário não encontrado ou sem permissão para excluí-lo.");
    }

    Long idNoticia = comentario.getNoticia().getIdNoticia();

    comentarioRepository.delete(comentario);

    return idNoticia;
     }
       
    
    }
