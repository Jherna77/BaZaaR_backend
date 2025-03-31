package com.jhernandez.backend.bazaar.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests
            .requestMatchers(HttpMethod.GET, "/api/users").permitAll() // Se permiten las peticiones GET a /api/users
            .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll() // Se permiten las peticiones POST a /api/users/register
            .anyRequest().authenticated()) // Las demás peticiones requieren autenticación
            .csrf(config -> config.disable()) // Deshabilita CSRF para evitar vulnerabilidades (p.e. en formularios)
            .sessionManagement(management ->
                management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sesión sin estado para manejar en el token todo lo relacionado con autenticación
            .build();
    }

}
