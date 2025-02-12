package com.example.scheduledevelop.controller;

import com.example.scheduledevelop.common.Const;
import com.example.scheduledevelop.dto.*;
import com.example.scheduledevelop.entity.User;
import com.example.scheduledevelop.repository.UserRepository;
import com.example.scheduledevelop.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //유저 생성
    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> createUser(@RequestBody SignupRequestDto dto) {
        SignupResponseDto user = userService.createUser(dto.getUsername(), dto.getEmail(), dto.getPassword());
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

    //유저이름 수정
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUsername (@PathVariable Long id, @RequestBody UpdateUsernameRequestDto dto) {
        UserResponseDto updateUser = userService.updateUsername(id, dto.getUsername());
        return ResponseEntity.ok(updateUser);
    }

    //유저 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login (
        @Valid @ModelAttribute LoginRequestDto dto,
        HttpServletRequest request
    ) {
        LoginResponseDto responseDto = userService.login(dto.getEmail(), dto.getPassword());
        Long userId = responseDto.getId();

        HttpSession session = request.getSession();

        UserResponseDto loginUser = userService.findById(userId);

        session.setAttribute(Const.LOGIN_USER, loginUser);

        return ResponseEntity.ok(responseDto);
    }

    //로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        // 현재 세션 가져오기
        HttpSession session = request.getSession(false);

        // 세션이 존재하면 무효화
        if (session != null) {
            session.invalidate();
        }

        // JSESSIONID 쿠키 삭제
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        cookie.setPath("/"); // 모든 경로에서 삭제
        response.addCookie(cookie);

        return ResponseEntity.noContent().build();
    }


}
