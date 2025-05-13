package com.jhernandez.backend.bazaar.infrastructure.security;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.UserEntity;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.JpaUserRepository;

import lombok.RequiredArgsConstructor;

/*
 * Service for loading user details from the database.
 */
@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService{

    private final JpaUserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("{authentication.invalid.email.message}");
        }

        UserEntity userEntity = userOptional.orElseThrow();

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(userEntity.getRole().getName()));
        
        return new User(
            userEntity.getEmail(),
            userEntity.getPassword(),
            userEntity.getEnabled(),
            true,
            true,
            true,
            authorities);    
    }

}
