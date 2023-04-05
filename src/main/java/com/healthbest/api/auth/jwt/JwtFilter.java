package com.healthbest.api.auth.jwt;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    private final JwtTokenExtractor jwtTokenExtractor;
    private final JwtTokenGenerator jwtTokenGenerator;
    private final JwtTokenValidator jwtTokenValidator;
    private final AuthenticationProvider authenticationProvider;
    private final String secretKey;

    public JwtFilter(JwtTokenExtractor jwtTokenExtractor,
                     JwtTokenGenerator jwtTokenGenerator,
                     JwtTokenValidator jwtTokenValidator,
                     AuthenticationProvider authenticationProvider,
                     @Value("${jwt.secret}") String secretKey) {
        this.jwtTokenExtractor = jwtTokenExtractor;
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.jwtTokenValidator = jwtTokenValidator;
        this.authenticationProvider = authenticationProvider;
        this.secretKey = secretKey;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(AUTHORIZATION_HEADER);

        if (!jwtTokenValidator.validateToken(token, secretKey)) {
            throw new RuntimeException("유효하지 않은 토큰입니다.");
        }

        String loginId = jwtTokenExtractor.extractToken(token, secretKey);
        Authentication authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(null, loginId)
        );

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
