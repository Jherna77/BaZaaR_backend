package com.jhernandez.backend.bazaar.secutity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
            .requestMatchers("/api/users").permitAll() // Se dejan públicos los endpoints relacionados con users
            .anyRequest().authenticated()) // Las demás peticiones requieren autenticación
            .csrf(config -> config.disable()) // Deshabilita CSRF para evitar vulnerabilidades (p.e. en formularios)
            .sessionManagement(management ->
                management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sesión sin estado para manejar en el token todo lo relacionado con autenticación
            .build();
    }

}
