package com.healthbest.api.auth.security.config;

import com.healthbest.api.auth.jwt.*;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenExtractor jwtTokenExtractor;
    private final JwtTokenValidator jwtTokenValidator;
    private final AuthenticationProvider authenticationProvider;
    private final String secretKey;

    public JwtSecurityConfig(JwtTokenExtractor jwtTokenExtractor,
                             JwtTokenValidator jwtTokenValidator,
                             AuthenticationProvider authenticationProvider,
                             String secretKey) {
        this.jwtTokenExtractor = jwtTokenExtractor;
        this.jwtTokenValidator = jwtTokenValidator;
        this.authenticationProvider = authenticationProvider;
        this.secretKey = secretKey;
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtFilter customFilter = new JwtFilter(
                jwtTokenExtractor,
                jwtTokenValidator,
                authenticationProvider,
                secretKey
        );

        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
