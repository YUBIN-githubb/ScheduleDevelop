package com.example.scheduledevelop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupResponseDto {

    private Long id;

    private String username;

    private String email;
}
