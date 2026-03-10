package com.example.forohub.controller;

import com.example.forohub.domain.Usuario;
import com.example.forohub.dto.DatosAutenticacionUsuario;
import com.example.forohub.dto.DatosTokenJWT;
import com.example.forohub.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public DatosTokenJWT autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datos) {

        var authenticationToken = new UsernamePasswordAuthenticationToken(
                datos.login(),
                datos.clave()
        );

        var autenticacion = manager.authenticate(authenticationToken);

        var usuario = (Usuario) autenticacion.getPrincipal();

        var token = tokenService.generarToken(usuario);

        return new DatosTokenJWT(token);
    }
}