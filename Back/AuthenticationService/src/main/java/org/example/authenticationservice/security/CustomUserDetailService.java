package org.example.authenticationservice.security;

import org.example.authenticationservice.entity.UtilisateurApp;
import org.example.authenticationservice.repository.UtilisateurAppRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service

public class CustomUserDetailService implements UserDetailsService {

    private final UtilisateurAppRepository utilisateurAppRepository;

    public CustomUserDetailService(UtilisateurAppRepository utilisateurAppRepository) {
        this.utilisateurAppRepository = utilisateurAppRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UtilisateurApp> userAppOptional = utilisateurAppRepository.findByEmail(email);
        if (userAppOptional.isPresent()){
            UtilisateurApp userApp = userAppOptional.get();
            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority("user"));
            return new User(userApp.getEmail(), userApp.getPassword(),authorities);
        }
        return null;
    }
}