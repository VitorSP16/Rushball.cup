
package br.com.rushball.rushball.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.rushball.rushball.entity.Usuario;
import br.com.rushball.rushball.repository.UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        
        Usuario usuario = usuarioRepository
                .findByEmailUsuarioIgnoreCase(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Usuário não encontrado: " + email));

        return User.builder()
                .username(usuario.getEmailUsuario())
                .password(usuario.getSenhaUsuario())
                .roles(usuario.getPapelUsuario())
                .build();
    }
}
