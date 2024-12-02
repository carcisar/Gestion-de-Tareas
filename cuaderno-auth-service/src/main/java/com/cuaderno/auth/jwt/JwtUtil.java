package com.cuaderno.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String jwtSecret = "q3z8Vh6qZ9Kc5N4tP0sJ1X6yB2eF9H7uI4aL0bM3cN8oQ5rS"; // Debe ser de al menos 32 caracteres
    private final long jwtExpirationMs = 86400000; // 1 día

    private final Key key;

    public JwtUtil() {
        // La clave secreta debe tener al menos 256 bits (32 bytes) para HS256
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    /**
     * Genera un token JWT basado en la autenticación
     */
    public String generateToken(Authentication authentication) {
        org.springframework.security.core.userdetails.User userPrincipal =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .claim("roles", userPrincipal.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Obtiene el nombre de usuario del token JWT
     */
    public String getUsernameFromJwt(String token) {
        return parseClaims(token).getSubject();
    }

    /**
     * Valida el token JWT
     */
    public boolean validateJwtToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (Exception e) {
            // Puedes manejar diferentes excepciones (por ejemplo, expiración, firma inválida)
            System.err.println("Token inválido o expirado: " + e.getMessage());
            return false;
        }
    }

    /**
     * Parsea las reclamaciones del token JWT
     */
    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
