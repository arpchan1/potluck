package com.app.potluck.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // Secret key for signing and verifying JWTs
    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Token expiration time (in milliseconds)
    private final long ACCESS_TOKEN_EXPIRATION = 15 * 60 * 1000; // 15 minutes

    /**
     * Generate a JWT token.
     * 
     * @param userId The subject (userId) for which the token is created.
     * @return The generated JWT token.
     */
    public String generateToken(Long userId) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256) // Explicitly specify signing key and algorithm
                .compact();
    }

    /**
     * Validate the JWT token.
     * 
     * @param token The JWT token.
     * @param userId The username (userId) to validate against the token's subject.
     * @return True if the token is valid, false otherwise.
     */
    public boolean validateToken(String token) {
        try {
            // Use JwtParserBuilder to parse and validate the token
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY) // Use the same key for validation
                    .build()
                    .parseClaimsJws(token); // Parses and validates token
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Token is invalid or expired
            return false;
        }
    }

    /**
     * Extract all claims from the token.
     * 
     * @param token The JWT token.
     * @return The claims extracted from the token.
     */
    public Long extractUserId(String token) {
        String userId =  Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); 
        return Long.valueOf(userId);
    }    
}
