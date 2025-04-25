package com.example.copsboot.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfiguration {
    @Bean
    SecurityFilterChain configureSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeHttpRequests -> authorizeRequests
                                                                            .requestMatchers(HttpMethod.OPTIONS, "/api/**").permitAll() // permite todas las solicitudes de opcion sin autenticación
                                                                            .requestMatchers("/api/**").authenticated() //cualquier punto de partida que empiece con /api necesita un usuario autenticado para que pueda acceder
                                                                            .anyRequest().authenticated())
                                                            .oauth2ResourceServer(it -> it.jwt(Customizer.withDefaults())); // configura la aplicación para que el servidor utilice JWT tokens( Un jwt token es un objeto codificado que contiene información del usuario autenticado. Es confiable porque está firmado digitalmente)
        return http.build();
    }
    
}
