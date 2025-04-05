package com.jhernandez.backend.bazaar.infrastructure.security;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.TokenJwtConfig.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/*
 * Filter for JWT authentication.
 * This filter is responsible for authenticating the user and generating a JWT token.
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        
        UserEntity user = null;
        String email = null;
        String password = null;

        try {
            user = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
            email = user.getEmail();
            password = user.getPassword();
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken authToken =
             new UsernamePasswordAuthenticationToken(email, password);

        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        User user = (User) authResult.getPrincipal();
        String email = user.getUsername();

        // Get the authorities from the authenticated user
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        Claims claims = Jwts.claims()
            .add("authorities", new ObjectMapper().writeValueAsString(authorities))
            .build();
        
        // Generate the JWT token
        String token = Jwts.builder()
            .subject(email)
            .claims(claims)
            .signWith(SECRET_KEY)
            .compact();
        
        // Add the token to the response header
        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);

        Map<String, String> body = Map.of(
            "email", email,
            "token", token);

        // Convert the body to JSON and write it to the response
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType(CONTENT_TYPE_JSON);
        response.setStatus(200);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
            
        Map<String, String> body = Map.of(
            //"message", "{authentication.invalid.credentials.message}",
            "error", failed.getMessage());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType(CONTENT_TYPE_JSON);
        response.setStatus(401);

    }

}
