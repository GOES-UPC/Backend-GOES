package com.simplife.skip.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CuentaPrincipal implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    private Cuenta cuenta;

    public CuentaPrincipal(final Cuenta cuenta){
        this.cuenta = cuenta;
    }


    @Override
    public Set<? extends GrantedAuthority> getAuthorities() {
        Set<Rol> roles = cuenta.getRoles();
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> authorities
                .add(new SimpleGrantedAuthority(role.getNombre().toString()))
        );
        return authorities;
    }

    public Long getId() {
        return cuenta.getId();
    }

    public String getEmail() {
        return cuenta.getCorreoUPC();
    }

    @Override
    public String getPassword() {
        return cuenta.getContrasena();
    }

    @Override
    public String getUsername() {
        return cuenta.getCodigoUpc();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

