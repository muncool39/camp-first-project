package com.sparta.project.service;


import com.sparta.project.config.jwt.TokenProvider;
import com.sparta.project.config.jwt.UserAuthentication;
import com.sparta.project.domain.User;
import com.sparta.project.dto.user.UserLoginRequest;
import com.sparta.project.dto.user.UserSignupRequest;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
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
        // TODO : 유효성 검사 필요
        userRepository.save(User.create(
                request.username(), passwordEncoder.encode(request.password()),
                request.nickname(), request.role())
        );
    }

    public String login(final UserLoginRequest request) {
        User user = userRepository.findByUsername(request.username()).orElseThrow(()->
                new CodeBloomException(ErrorCode.USER_NOT_FOUND));
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new CodeBloomException(ErrorCode.INVALID_PASSWORD);
        }
        UserAuthentication userAuthentication = new UserAuthentication(user.getUserId(), null, null);
        return tokenProvider.generateToken(userAuthentication);
    }

}
