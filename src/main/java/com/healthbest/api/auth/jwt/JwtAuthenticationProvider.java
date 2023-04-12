package com.healthbest.api.auth.jwt;

import com.healthbest.api.auth.security.UserAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserAdapter userDetails = (UserAdapter) userDetailsService.loadUserByUsername(authentication.getPrincipal().toString());
        return new UsernamePasswordAuthenticationToken(userDetails.getUser(), null, null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
