package com.example.scheduledevelop.entity;

import com.example.scheduledevelop.dto.UserResponseDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.annotations.NotFound;

@Entity
@Table(name = "user")
@Getter
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank
    @Size(min = 1, max = 10)
    private String username;

    @Column(unique = true, nullable = false)
    @NotBlank
    //이메일 정규표현식 validation (exam@exam.com)
    @Pattern(regexp = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$"
            ,message = "이메일은 exam@exam.com 형식이어야 합니다. ")
    private String email;

    // 회원가입 요청 dto에 password 추가
    @NotBlank
    @Size(min = 4)
    @Pattern(regexp = "^(?=.*[!@#$%^&*(),.?\":{}|<>]).*$",
            message = "비밀번호는 적어도 하나의 특수문자를 포함해야 합니다.")
    private String password;

    public User() {

    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }

    public void updateUsername(String username) {
        this.username = username;
    }
}
