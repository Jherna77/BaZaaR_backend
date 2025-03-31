package com.jhernandez.backend.bazaar.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import static com.jhernandez.backend.bazaar.configuration.TokenJwtConfig.*;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidationFilter extends BasicAuthenticationFilter{

    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String header = request.getHeader(HEADER_AUTHORIZATION);

        // Check if the header is null or does not start with the prefix token
        if (header == null || !header.startsWith(PREFIX_TOKEN)) {
            chain.doFilter(request, response); // Continue with the filter chain
            return;
        }

        String token = header.replace(PREFIX_TOKEN, "");

        try {
            Claims claims = Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            
            String email = claims.getSubject();
            Object authorities = claims.get("authorities");

            Collection<? extends GrantedAuthority> roles = Arrays.asList(new ObjectMapper()
                .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class) // Acoplar el constructor de SimpleGrantedAuthority a la deserialización
                .readValue(authorities.toString().getBytes(), SimpleGrantedAuthority[].class));

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    email, null, roles);
            
            SecurityContextHolder.getContext().setAuthentication(authToken);
            chain.doFilter(request, response); // Continue with the filter chain

        } catch (JwtException e) {
            Map<String, String> body = Map.of("error", e.getMessage());

            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setStatus(401); // Unauthorized
            response.setContentType(CONTENT_TYPE_JSON);
        } 

        // super.doFilterInternal(request, response, chain);
    }

}
