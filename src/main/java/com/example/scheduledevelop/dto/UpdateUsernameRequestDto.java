package com.example.scheduledevelop.dto;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class UpdateUsernameRequestDto {

    private String username;
}
