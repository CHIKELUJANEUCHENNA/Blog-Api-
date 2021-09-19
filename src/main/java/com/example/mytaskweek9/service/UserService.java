package com.example.mytaskweek9.service;

import com.example.mytaskweek9.dto.LoginDto;
import com.example.mytaskweek9.dto.SignUpDto;
import com.example.mytaskweek9.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    ResponseEntity<?> loginUser(LoginDto loginDto);
    ResponseEntity<?> signUp(SignUpDto signUpDto);
    List<User> getAllUser();
    Optional<User> findUserById(long id);
    User updateUser(long id, User user);
    void deleteUser(long id);
    void cancelDelete(long userId);



}
