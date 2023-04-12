package com.healthbest.api.auth.controller;

import com.healthbest.api.auth.dto.AuthRequest;
import com.healthbest.api.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.healthbest.api.auth.dto.AuthResponse.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/sign-up")
    public ResponseEntity<SignUp> signUp(@RequestBody AuthRequest.SignUp request) {
        SignUp response = authService.signUp(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/auth/sign-in")
    public ResponseEntity<SignIn> signUp(@RequestBody AuthRequest.SignIn request) {
        SignIn response = authService.signIn(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
