package com.example.adminService.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    /*
     * This utility class provides methods for generating and validating JWT tokens.
     * It uses a secret key for signing the tokens and includes methods to extract the username and validate the token.
     * The secret key and expiration time are configured via application properties.
     */
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration:86400000}") // default 1 day
    private long expiration;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String generateToken(UserDetails userDetails) {
        // This method generates a JWT token for the given user details
        // It sets the subject as the username, issues the token at the current time, and sets the expiration time
        // The token is signed with the secret key using HS256 algorithm
        return Jwts.builder()
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    public String extractUsername(String token) {
        // This method extracts the username from the JWT token
        // It parses the token using the secret key and retrieves the subject (username)    
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        // This method validates the JWT token
        // It checks if the token is not expired and if the username in the token matches the user details
        // It returns true if the token is valid, otherwise false
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        // This method checks if the JWT token is expired
        // It parses the token and retrieves the expiration date, then checks if the current date is before the expiration date
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getExpiration()
            .before(new Date());
    }
}

