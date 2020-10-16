package com.simplife.skip.services.implementation;

import com.simplife.skip.models.Cuenta;
import com.simplife.skip.models.CuentaPrincipal;
import com.simplife.skip.repositories.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CuentaServiceImpl implements UserDetailsService {

    @Autowired
    CuentaRepository cuentaRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cuenta user = cuentaRepository.findByCodigoUpc(username);

        return new CuentaPrincipal(user);
    }
}

