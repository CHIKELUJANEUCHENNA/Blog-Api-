package com.example.mytaskweek9.controller;

import com.example.mytaskweek9.dto.LoginDto;
import com.example.mytaskweek9.dto.SignUpDto;
import com.example.mytaskweek9.enums.Status;
import com.example.mytaskweek9.model.User;
import com.example.mytaskweek9.repository.UserRepository;
import com.example.mytaskweek9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api",produces = "application/json")
public class LoginController {
    private final UserRepository userRepository;
    private final UserService userService;
    @Autowired
    public LoginController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto signUpDto) {
        return userService.signUp(signUpDto);
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginDto loginDto) {
        return userService.loginUser(loginDto);
    }

//    @PostMapping("/users/logout")
//    public Status userLogout(@Valid @RequestBody User user) {
//        List<User> users = userRepository.findAll();
//        for (User user2 : users) {
//            if (user2.equals(user)) {
////                user.setLoggedIn(false);
//                userRepository.save(user);
//                return Status.SUCCESS;
//            }
//        }
//        return Status.FAILURE;
//    }
}
