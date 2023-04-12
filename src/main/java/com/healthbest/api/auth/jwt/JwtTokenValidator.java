package com.healthbest.api.auth.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Slf4j
@Component
public class JwtTokenValidator {

    public boolean validateToken(String token, String secretKey) {
        String key = Base64.getEncoder().encodeToString(secretKey.getBytes());

        try {
            Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
