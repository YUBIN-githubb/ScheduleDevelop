package com.example.scheduledevelop.controller;

import com.example.scheduledevelop.dto.SignupRequestDto;
import com.example.scheduledevelop.dto.SignupResponseDto;
import com.example.scheduledevelop.entity.User;
import com.example.scheduledevelop.repository.UserRepository;
import com.example.scheduledevelop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //유저 생성
    @PostMapping
    public ResponseEntity<SignupResponseDto> createUser(@RequestBody SignupRequestDto dto) {
        SignupResponseDto user = userService.createUser(dto.getUsername(), dto.getEmail());
        return ResponseEntity.ok(user);
    }
}
