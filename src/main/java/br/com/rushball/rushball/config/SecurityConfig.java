
package br.com.rushball.rushball.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth

                .requestMatchers(
                    "/",
                    "/error",
                    "/css/**",
                    "/images/**",
                    "/webjars/**",
                    "/usuarios/cadastrar"
                ).permitAll()

                .requestMatchers(
                    HttpMethod.GET,
                    "/selecoes/**",
                    "/jogadores/**",
                    "/jogos/**",
                    "/estatisticas/**",
                    "/noticias/**",
                    "/comentarios/noticia/**"
                ).permitAll()

                .requestMatchers(
                    HttpMethod.POST,
                    "/usuarios/cadastrar"
                ).permitAll()

                .requestMatchers(
                    HttpMethod.POST,
                    "/comentarios/publicar/**"
                ).authenticated()

                .requestMatchers("/usuarios/**").authenticated()

                .anyRequest().authenticated()
            )

            .formLogin(form -> form
                .usernameParameter("email")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )

            .logout(logout -> logout
                .logoutSuccessUrl("/")
            );

        return http.build();
    }
}
