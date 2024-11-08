package com.sparta.project.controller;


import com.sparta.project.dto.user.UserLoginRequest;
import com.sparta.project.dto.user.UserSignupRequest;
import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.service.UserService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ApiResponse<Void> signup(@RequestBody UserSignupRequest request) {
        userService.createUser(request);
        return ApiResponse.success("회원가입 성공");
    }

    @PostMapping("/login")
    public ApiResponse<Map<String, String>> login(@RequestBody UserLoginRequest loginUserRequest) {
        String token = userService.login(loginUserRequest);
        return ApiResponse.success("로그인 성공", Map.of("token", token));
    }

}
