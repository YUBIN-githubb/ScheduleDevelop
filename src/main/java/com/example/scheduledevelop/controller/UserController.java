package com.example.scheduledevelop.controller;

import com.example.scheduledevelop.dto.SignupRequestDto;
import com.example.scheduledevelop.dto.SignupResponseDto;
import com.example.scheduledevelop.dto.UserResponseDto;
import com.example.scheduledevelop.entity.User;
import com.example.scheduledevelop.repository.UserRepository;
import com.example.scheduledevelop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //유저 전체 조회
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {
        List<UserResponseDto> userList = userService.findAll();
        return ResponseEntity.ok(userList);
    }

    //유저 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        UserResponseDto findUser = userService.findById(id);
        return ResponseEntity.ok(findUser);
    }

}
