package com.example.scheduledevelop.dto.SignupDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupRequestDto {

    private String username;

    private String email;

    private String password;
}
