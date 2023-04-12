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

    @Value("${jwt.expire-time}")
    private Long expiredTime;

    @Value("${jwt.secret}")
    private String secretKey;


    public String generateToken(String loginId) {
        log.info("secretKey : {}", secretKey);
        log.info("expiredTime : {}", expiredTime);

        String key = Base64.getEncoder().encodeToString(secretKey.getBytes());
        long expiredTimeMilli = expiredTime * 1000L;
        Date now = new Date();
        Claims claims = Jwts.claims().setSubject(loginId);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expiredTimeMilli))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
}
