package com.sparta.project.dto.user;

import com.sparta.project.domain.enums.Role;
import jakarta.validation.constraints.NotBlank;

public record UserSignupRequest(
        @NotBlank String username,
        @NotBlank String password,
        @NotBlank String nickname,
        @NotBlank Role role
) {
}
