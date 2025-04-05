package com.jhernandez.backend.bazaar.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jhernandez.backend.bazaar.infrastructure.security.JwtAuthenticationFilter;
import com.jhernandez.backend.bazaar.infrastructure.security.JwtValidationFilter;

import lombok.RequiredArgsConstructor;

/*
 * Spring Security configuration class.
 * This class configures the security settings for the application, including authentication and authorization.
 */
@Configuration
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests
            .requestMatchers(HttpMethod.GET, "/api/users").permitAll() // Se permiten las peticiones GET a /api/users
            .requestMatchers(HttpMethod.POST, "/api/users").permitAll() // Se permiten las peticiones POST a /api/users/register
            .anyRequest().authenticated()) // Las demás peticiones requieren autenticación
            .addFilter(new JwtAuthenticationFilter(authenticationManager())) // Se añade el filtro de autenticación JWT
            .addFilter(new JwtValidationFilter(authenticationManager())) // Se añade el filtro de validación JWT
            .csrf(config -> config.disable()) // Deshabilita CSRF para evitar vulnerabilidades (p.e. en formularios)
            .sessionManagement(management ->
                management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sesión sin estado para manejar en el token todo lo relacionado con autenticación
            .build();
    }

}
