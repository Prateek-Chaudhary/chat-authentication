package com.prateek.authentication.services;

import com.prateek.authentication.configs.SecretKeyConfigs;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {

    private final SecretKeyConfigs secretKeyConfigs;

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public String extractUserId(String token) {
        return extractClaims(token, claims -> claims.get("userId", String.class));
    }

    private <T> T extractClaims(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey(secretKeyConfigs.getSecretKeys().get(secretKeyConfigs.getActiveKey())))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenValid(String token, UserDetails user) {
        try {
            Jws<Claims> jws = Jwts.parser()
                    .verifyWith(getSignKey(secretKeyConfigs.getSecretKeys().get(secretKeyConfigs.getActiveKey())))
                    .build()
                    .parseSignedClaims(token);

            // Now get header safely
            JwsHeader header = jws.getHeader();
            String kid = (String) header.get("kid");
            if(kid == null || !secretKeyConfigs.getSecretKeys().containsKey(kid)) return false;

            Claims claims = Jwts.parser()
                    .verifyWith(getSignKey(secretKeyConfigs.getSecretKeys().get(kid)))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            String username = claims.getSubject();
            return username.equals(user.getUsername()) && !isTokenExpired(token);
        }
        catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    public String generateAccessToken(UserDetails user, String userId) {
        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return Jwts.builder()
                .header().add("kid", secretKeyConfigs.getActiveKey()).and()
                .subject(user.getUsername())
                .signWith(getSignKey(secretKeyConfigs.getSecretKeys().get(secretKeyConfigs.getActiveKey())))
                .claim("roles", roles)
                .claim("userId", userId)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (1000*60*60*24)))
                .compact();
    }

    public String generateRefreshToken(UserDetails user, String userId) {
        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return Jwts.builder()
                .header().add("kid", secretKeyConfigs.getActiveKey()).and()
                .subject(user.getUsername())
                .signWith(getSignKey(secretKeyConfigs.getSecretKeys().get(secretKeyConfigs.getActiveKey())))
                .claim("roles", roles)
                .claim("userId", userId)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (1000*60*60*24*7)))
                .compact();
    }

    private SecretKey getSignKey(String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}