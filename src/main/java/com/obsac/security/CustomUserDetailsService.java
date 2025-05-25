package com.obsac.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("usuario".equals(username)) {
            return User.withUsername("usuario")
                    .password(new BCryptPasswordEncoder().encode("1234"))
                    .roles("USER")
                    .build();
        }
        throw new UsernameNotFoundException("Usuario no encontrado");
    }
}
