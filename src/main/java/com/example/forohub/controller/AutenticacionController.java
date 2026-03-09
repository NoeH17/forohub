package com.example.forohub.controller;

import com.example.forohub.dto.DatosAutenticacionUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity autenticarUsuario(
            @RequestBody @Valid DatosAutenticacionUsuario datos) {

        Authentication authToken =
                new UsernamePasswordAuthenticationToken(
                        datos.login(),
                        datos.clave()
                );

        var autenticacion = authenticationManager.authenticate(authToken);

        return ResponseEntity.ok().build();
    }
}