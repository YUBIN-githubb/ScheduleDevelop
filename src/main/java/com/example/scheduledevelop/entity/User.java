package com.example.scheduledevelop.entity;

import com.example.scheduledevelop.dto.UserResponseDto;
import jakarta.persistence.*;
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
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    // 회원가입 요청 dto에 password 추가
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
