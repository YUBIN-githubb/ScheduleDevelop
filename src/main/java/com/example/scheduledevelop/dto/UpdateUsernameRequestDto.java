package com.example.scheduledevelop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@AllArgsConstructor
public class UpdateUsernameRequestDto {

    private String username;
}
