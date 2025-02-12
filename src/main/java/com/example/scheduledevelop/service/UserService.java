package com.example.scheduledevelop.service;

import com.example.scheduledevelop.dto.LoginResponseDto;
import com.example.scheduledevelop.dto.SignupResponseDto;
import com.example.scheduledevelop.dto.UserResponseDto;
import com.example.scheduledevelop.entity.User;
import com.example.scheduledevelop.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public SignupResponseDto createUser(String username, String email, String password) {
        User user = new User(username, email, password);
        User savedUser = userRepository.save(user);

        return new SignupResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }

    public List<UserResponseDto> findAll() {
        List<UserResponseDto> userList = userRepository.findAll().stream().map(User::toDto).toList();
        return userList;
    }

    public UserResponseDto findById(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        return new UserResponseDto(findUser.getId(), findUser.getUsername(), findUser.getEmail());
    }

    @Transactional
    public UserResponseDto updateUsername(Long id, String username) {
        User findUser = userRepository.findByIdOrElseThrow(id);

        //username은 unique
        Optional<User> byUsername = userRepository.findByUsername(username);

        //만약 새로운 username이 DB에 없다면 -> 수정 허용
        if(byUsername.isEmpty()){
            findUser.updateUsername(username);
            return new UserResponseDto(findUser.getId(), findUser.getUsername(), findUser.getEmail());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
    }

    public void deleteUser (Long id) {
        User toDeleteUser = userRepository.findByIdOrElseThrow(id);
        userRepository.delete(toDeleteUser);
    }

    public LoginResponseDto login(String email, String password) {
        //받은 email과 password로 DB조회
        Optional<User> findUser = userRepository.findByEmailAndPassword(email, password);
        if (findUser.isEmpty()){
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "일치하는 유저가 없습니다.");
        }
        User user = findUser.get();

        return new LoginResponseDto(user.getId(), user.getUsername());
    }
}
