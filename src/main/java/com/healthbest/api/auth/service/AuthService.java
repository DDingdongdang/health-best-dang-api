package com.healthbest.api.auth.service;

import com.healthbest.api.auth.dto.AuthRequest;
import com.healthbest.api.auth.jwt.JwtTokenGenerator;
import com.healthbest.api.user.domain.User;
import com.healthbest.api.user.domain.vo.Gender;
import com.healthbest.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.healthbest.api.auth.dto.AuthResponse.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenGenerator jwtTokenGenerator;

    @Transactional
    public SignUp signUp(AuthRequest.SignUp request) {
        User user = userRepository.save(new User(
                request.getLoginId(),
                request.getPassword(),
                request.getNickname(),
                request.getGender(),
                request.getAge(),
                request.getHeight(),
                request.getWeight()
        ));

        return new SignUp(user.getId());
    }

    @Transactional
    public SignIn signIn(AuthRequest.SignIn request) {
        User user = userRepository.findByLoginId(request.getLoginId())
                .orElseThrow(() -> new RuntimeException("존재하지 않은 유저입니다."));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("잘못된 비밀번호 입니다.");
        }

        String token = jwtTokenGenerator.generateToken(request.getLoginId());

        return new SignIn(user.getId(), token);
    }
}
