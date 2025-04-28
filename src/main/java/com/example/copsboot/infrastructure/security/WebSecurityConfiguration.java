package com.example.copsboot.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // si es una API REST stateless suele ser buena idea desactivar CSRF
                .csrf(csrf -> csrf.disable())

                // configuramos quién puede acceder a qué rutas
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()   // permitir CORS preflight
                        .requestMatchers("/api/**").authenticated()                   // todo /api/** requiere JWT válido
                        .anyRequest().permitAll()                                      // el resto (páginas estáticas, swagger, etc) queda abierto
                )

                // establecemos que usamos OAuth2 Resource Server con tokens JWT
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults())
                );

        return http.build();
    }
}
