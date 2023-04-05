package com.healthbest.api.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;


@Slf4j
@Component
public class JwtTokenGenerator {
    private static final String BEARER_TYPE = "Bearer";
    private final long expiredTime;
    private final String secreteKey;

    public JwtTokenGenerator(@Value("${jwt.secret}") String secreteKey,
                             @Value("${jwt.expired-time") long expiredTime) {
        this.expiredTime = expiredTime;
        this.secreteKey = secreteKey;
    }

    public String generateToken(String loginId) {

        Date now = new Date();
        Claims claims = Jwts.claims().setSubject(loginId);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expiredTime))
                .signWith(SignatureAlgorithm.HS512, secreteKey)
                .compact();
    }
}
