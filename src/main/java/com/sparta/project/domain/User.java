package com.sparta.project.domain;

import com.sparta.project.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Entity
@Table(
    name="p_user",
    uniqueConstraints = {
        @UniqueConstraint(name="UK_USER", columnNames={"username"})
    }
)
public class User extends BaseEntity { // 유저

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id", nullable=false, updatable=false)
    private Long userId;

    @Column(name="username", length=10, nullable=false) // 유저 고유 네임 (사용자 id)
    private String username;

    @Column(name="password", length=255, nullable=false) // 비밀번호
    private String password;

    @Column(name="nickname", length=10) // 닉네임
    private String nickname;

    @Column(name="role", nullable=false) // 권한
    @Enumerated(EnumType.STRING)
    private Role role;

}
