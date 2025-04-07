package com.jhernandez.backend.bazaar.infrastructure.configuration;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jhernandez.backend.bazaar.infrastructure.security.JwtAuthenticationFilter;
import com.jhernandez.backend.bazaar.infrastructure.security.JwtValidationFilter;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.ApiRoutes.*;

/*
 * Spring Security configuration class.
 * This class configures the security settings for the application, including authentication and authorization.
 */
@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

  /* Configuration for JWT tokens:
   *    Secret key used for signing the tokens
   *    Prefix for the token
   *    Header name for authorization
   *    Content type for JSON/
   */
    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE_JSON = "application/json";

    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * SecurityFilterChain bean that configures the security filter chain for the application.
     * It specifies the authorization rules for different endpoints and adds JWT filters for authentication and validation.
     * Public endpoints:
     *      - GET /api/categories
     *      - GET /api/products
     *      - GET /api/categories/{id}
     *      - GET /api/products/{id}
     *      - GET /api/products/category/{categoryId}
     *      - POST /api/users/register
     * 
     * All other endpoints require authentication.
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests
            .requestMatchers(HttpMethod.GET, CATEGORIES, PRODUCTS, CATEGORY_ID, PRODUCT_ID, PRODUCTS_CATEGORY_ID).permitAll()
            .requestMatchers(HttpMethod.POST, REGISTER).permitAll()
            .anyRequest().authenticated())
            // .anyRequest().permitAll()) // Allow all requests for testing purposes
            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
            .addFilter(new JwtValidationFilter(authenticationManager()))
            .csrf(config -> config.disable()) // Disable CSRF to avoid vulnerabilities
            .sessionManagement(management ->
                management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session to handle authentication in token
            .build();
    }

}
