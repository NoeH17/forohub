package com.example.forohub.security;

import com.example.forohub.domain.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generarToken(Usuario usuario) {

        try {

            Algorithm algoritmo = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("foro-api")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algoritmo);

        } catch (Exception e) {
            throw new RuntimeException("Error al generar token", e);
        }
    }

    public String getSubject(String token) {

        try {

            Algorithm algoritmo = Algorithm.HMAC256(secret);

            return JWT.require(algoritmo)
                    .withIssuer("foro-api")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (Exception e) {
            return null;
        }
    }

    private Instant fechaExpiracion() {
        return Instant.now().plusSeconds(expiration * 3600L);
    }
}