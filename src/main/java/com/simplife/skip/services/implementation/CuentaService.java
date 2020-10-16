package com.simplife.skip.services.implementation;

import com.simplife.skip.models.Cuenta;
import com.simplife.skip.models.Rol;
import com.simplife.skip.repositories.CuentaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
@Slf4j
public class CuentaService implements UserDetailsService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Cuenta cuenta = cuentaRepository.findByCodigoUpc(username);

        if(cuenta == null){
            throw new UsernameNotFoundException(username);
        }

        List<GrantedAuthority> roles = new ArrayList<>();

        for(Rol rol: cuenta.getRoles()){
            roles.add(new SimpleGrantedAuthority(rol.getNombre().toString()));
        }

        return new User(cuenta.getCodigoUpc(), cuenta.getContrasena(), roles);

    }
}

