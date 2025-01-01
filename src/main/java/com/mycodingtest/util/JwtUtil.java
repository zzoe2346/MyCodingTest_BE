package com.mycodingtest.util;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    private static final SecretKey KEY = Jwts.SIG.HS256.key().build();
    private static final int JWT_EXPIRATION_IN_MS = 1000 * 60 * 60 * 2;

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS))
                .signWith(KEY)
                .compact();
    }

    public String validateAndExtractUsername(String token) {
        return Jwts.parser().verifyWith(KEY).build().parseSignedClaims(token).getPayload().getSubject();
    }
}
