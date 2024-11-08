package com.sparta.project.service;


import com.sparta.project.config.jwt.TokenProvider;
import com.sparta.project.domain.User;
import com.sparta.project.dto.user.UserSignupRequest;
import com.sparta.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(final UserSignupRequest request) {
        // TODO : 유효성 검사, 정적 메서드 사용 수정 필요
        userRepository.save(User.builder()
                .username(request.username())
                .nickname(request.nickname())
                .password(passwordEncoder.encode(request.password()))
                .role(request.role())
                .build()
        );
    }

}
