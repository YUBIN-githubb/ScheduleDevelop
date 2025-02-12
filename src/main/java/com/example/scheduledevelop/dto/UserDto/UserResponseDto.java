package com.example.scheduledevelop.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    private Long id;

    private String username;

    private String email;
}
