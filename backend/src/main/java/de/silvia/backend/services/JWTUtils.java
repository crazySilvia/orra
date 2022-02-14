package de.silvia.backend.services;

import de.silvia.backend.security.models.LoginData;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

@Service
public class JWTUtils {

    @Value(value = "${secret}")
    private String secret;

    public String createToken(LoginData user) {
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(user.getName())
                //Ablauffrist:
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(Duration.ofHours(12))))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String extractUserName(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getExpiration().before(new Date());
    }

    public Boolean validateToken(String token, String username) {
        String userName = extractUserName(token);
        return (userName.equals(username) && !isTokenExpired(token) && !token.isBlank());
    }
}