package com.obsac.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;

public class CustomRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        Map<String, Object> claims = jwt.getClaims();

        if (claims.containsKey("realm_access")) {
            Map<String, Object> realmAccess = (Map<String, Object>) claims.get("realm_access");
            if (realmAccess.containsKey("roles")) {
                List<String> roles = (List<String>) realmAccess.get("roles");
                for (String role : roles) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
                }
            }
        }

        return authorities;
    }
}
