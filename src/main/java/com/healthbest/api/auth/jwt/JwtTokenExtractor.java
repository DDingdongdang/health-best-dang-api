package com.healthbest.api.auth.jwt;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Slf4j
@Component
public class JwtTokenExtractor {

    public String extractToken(String accessToken, String secretKey) {
        String key = Base64.getEncoder().encodeToString(secretKey.getBytes());

        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(accessToken)
                .getBody()
                .getSubject();
    }
}
