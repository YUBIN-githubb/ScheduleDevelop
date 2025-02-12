package com.example.scheduledevelop.repository;

import com.example.scheduledevelop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    default User findByIdOrElseThrow(long id) {
        return findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found"));
    }

    Optional<User> findByUsername (String username);

    default User findByUsernameOrElseThrow(String username) {
        return findByUsername(username).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User with username " + username + " not found"));
    }

    Optional<User> findByEmailAndPassword (String email, String password);

//    default User findIdByEmailAndPasswordOrElseThrow (String email, String password) {
//        Long findId = findIdByEmailAndPassword(email, password);
//        if (findId == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with email " + email + " not found");
//        }
//        else {
//            Optional<User> findUser = findById(findId);
//            return findUser.get();
//        }
//    }
}
