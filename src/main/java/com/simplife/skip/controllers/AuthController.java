package com.simplife.skip.controllers;

import com.simplife.skip.models.*;
import com.simplife.skip.payload.requests.JwtResponse;
import com.simplife.skip.payload.requests.LoginRequest;
import com.simplife.skip.payload.requests.MessageResponse;
import com.simplife.skip.payload.requests.SignUpRequest;
import com.simplife.skip.repositories.*;
import com.simplife.skip.security.jwt.JwtUtils;
import com.simplife.skip.utils.Conversor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth/usuario")
@Slf4j
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private InformacionConductorRepository informacionConductorRepository;

    @Autowired
    private AutoRepository autoRepository;

    @Autowired
    private PasswordEncoder encoder;


    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> loginCuenta(final @Valid @RequestBody LoginRequest loginRequest){

        //Para procesar el nombre de usuario y contraseÃ±a y autenticarlos
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getCodigo(), loginRequest.getContrasena()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        //Generamos el token
        String jwt = jwtUtils.generateJwtToken(authentication);

        //obtenemos el usuario
        CuentaPrincipal userDetails = (CuentaPrincipal) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        Long usuarioId = usuarioRepository.obtenerIdPorCuenta(userDetails.getId());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                usuarioId,
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));

    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrarCuenta(final @Valid @RequestBody SignUpRequest signUpRequest) throws Exception{

        log.info("***********************************");
        log.info(signUpRequest.toString());
        log.info("***********************************");
        String correo = Conversor.convertirACorreo(signUpRequest.getCodigo());
        if(cuentaRepository.existsByCodigoUpc(signUpRequest.getCodigo())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Este nombre de usuario ya esta tomado!"));
        }
        else if(cuentaRepository.existsByCorreoUPC(correo)){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Este Email ya esta en uso!"));
        }
        else{

            Cuenta cuenta = new Cuenta(signUpRequest.getCodigo(), correo, encoder.encode(signUpRequest.getContrasena()));

            Set<String> strRoles = signUpRequest.getRole();
            Set<Rol> roles = new HashSet<>();
            InformacionConductor info = null;

            String runTimeExceptionMessage = "Error: Role is not found.";
            if (strRoles == null) {
                Rol userRole = rolRepository.findByNombre(ERole.ROL_PASAJERO)
                        .orElseThrow(() -> new RuntimeException(runTimeExceptionMessage));
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "conductor":
                            Rol modRole = rolRepository.findByNombre(ERole.ROL_CONDUCTOR)
                                    .orElseThrow(() -> new RuntimeException(runTimeExceptionMessage));
                            roles.add(modRole);
                            break;

                        case "pasajero" :
                            Rol userRole = rolRepository.findByNombre(ERole.ROL_PASAJERO)
                                    .orElseThrow(() -> new RuntimeException(runTimeExceptionMessage));
                            roles.add(userRole);
                            break;

                    }
                });
            }


            cuenta.setRoles(roles);

            cuentaRepository.save(cuenta);

            Usuario usuario = new Usuario(signUpRequest.getDni(),signUpRequest.getNombres(), signUpRequest.getApellidos(), signUpRequest.getSede(), cuenta, signUpRequest.getImagen());
            Usuario auxUsuario = usuarioRepository.save(usuario);

            for(Rol rol: roles){
                if(rol.getNombre() == ERole.ROL_CONDUCTOR && signUpRequest.getInfoConductor() != null
                    && signUpRequest.getAuto() != null){
                    InformacionConductor auxInfo = signUpRequest.getInfoConductor();
                    auxInfo.setEstadoTabla(true);
                    auxInfo.setUsuario(auxUsuario);
                    info = informacionConductorRepository.save(auxInfo);
                    Auto auxAuto = signUpRequest.getAuto();
                    auxAuto.setEstadoTabla(true);
                    auxAuto.setInfoConductor(info);
                    autoRepository.save(auxAuto);

                }
            }
            if (info != null)
            {
                return ResponseEntity.ok(new MessageResponse(auxUsuario.toString() +","+ info.toString()));
            }
            else
            {
                return ResponseEntity.ok(new MessageResponse(auxUsuario.toString()));

            }


        }

    }




}
