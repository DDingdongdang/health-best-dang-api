package com.healthbest.api.auth.jwt;

import com.healthbest.api.user.domain.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final JwtTokenExtractor jwtTokenExtractor;
    private final JwtTokenValidator jwtTokenValidator;
    private final AuthenticationProvider authenticationProvider;
    private final String secretKey;

    public JwtFilter(JwtTokenExtractor jwtTokenExtractor,
                     JwtTokenValidator jwtTokenValidator,
                     AuthenticationProvider AuthenticationProvider,
                     @Value("${jwt.secret}") String secretKey) {
        this.jwtTokenExtractor = jwtTokenExtractor;
        this.jwtTokenValidator = jwtTokenValidator;
        this.authenticationProvider = AuthenticationProvider;
        this.secretKey = secretKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(AUTHORIZATION_HEADER);
        String token = getTokenFromHeader(header);

        log.info("token : {}", token);
        log.info("key : {}", secretKey);

        if (!jwtTokenValidator.validateToken(token, secretKey)) {
            throw new RuntimeException("유효하지 않은 토큰입니다.");
        }

        String loginId = jwtTokenExtractor.extractToken(token, secretKey);
        Authentication authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(loginId, null)
        );

        User user = (User) authentication.getPrincipal();
        log.info("@@ user id : {}", user.getId());
        log.info("@@ user loginId : {}", user.getLoginId());

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        log.info("Security Context에 '{}' 인증 정보를 저장했습니다", authentication.getName());

        filterChain.doFilter(request, response);
    }

    private String getTokenFromHeader(String header) {
        String[] token = header.split(" ");
        if (token.length != 2 || !token[0].equals("Bearer")) {
            throw new RuntimeException("잘못된 형식의 Authorization 헤더입니다.");
        }
        return token[1];
    }
}
