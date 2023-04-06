package com.healthbest.api.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthbest.api.auth.dto.AuthRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JwtTokenExtractor jwtTokenExtractor;
    private final JwtTokenValidator jwtTokenValidator;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Value("${jwt.secret}")
    private String secretKey;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(AUTHORIZATION_HEADER);

        if (!jwtTokenValidator.validateToken(token, secretKey)) {
            throw new RuntimeException("유효하지 않은 토큰입니다.");
        }

        AuthRequest.SignIn signIn = objectMapper.readValue(request.getReader(), AuthRequest.SignIn.class);

        String loginId = jwtTokenExtractor.extractToken(token, secretKey);
        Authentication authentication = jwtAuthenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getPassword(), loginId)
        );

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
