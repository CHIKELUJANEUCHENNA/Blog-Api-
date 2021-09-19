package com.example.mytaskweek9.service.implementation;

import com.example.mytaskweek9.controller.PostController;
import com.example.mytaskweek9.dto.LoginDto;
import com.example.mytaskweek9.dto.SignUpDto;
import com.example.mytaskweek9.exceptions.AppException;
import com.example.mytaskweek9.exceptions.UserRegistrationException;
import com.example.mytaskweek9.model.*;
import com.example.mytaskweek9.repository.*;
import com.example.mytaskweek9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServicesImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public ResponseEntity<?> loginUser(LoginDto loginDto) {
        Optional<User> user = userRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        if(user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        throw new AppException("Incorrect password", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> signUp(SignUpDto signUpDto) {
        User user = new User();
        if (user != null) {
            user.setEmail(signUpDto.getEmail());
            user.setFullName(signUpDto.getFullName());
            user.setPassword(signUpDto.getPassword());
            user.setPhoneNumber(signUpDto.getPhoneNumber());
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
        } else {
            throw new UserRegistrationException("User with email " + user.getEmail() + " already exists");
        }
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(long id) {
        return userRepository.findById(id);
//        User user = new User();
//        if (user == null) {
//            throw new AppException("User not found", HttpStatus.BAD_REQUEST);
//        } else {
//            return userRepository.findById(id).get();
//        }
    }

    @Override
        public User updateUser(long id, User user) {
        Optional<User> user1 = userRepository.findById(id);
        if (user1.isEmpty()) {
            throw new AppException("User not found", HttpStatus.BAD_REQUEST);
        }
        else
            user1.get().setEmail(user.getEmail());
        user1.get().setFullName(user.getFullName());
        user1.get().setPassword(user.getPassword());
        user1.get().setPhoneNumber(user.getPhoneNumber());
        return userRepository.save(user1.get());
    }

    @Override
    public void deleteUser(long id) {
        User user = userRepository.getById(id);
        user.setFlag(true);
        userRepository.save(user);
    }
    @Override
    public void cancelDelete(long userId) {
        User user = userRepository.getById(userId);
        if (user.isFlag()) {
            user.setFlag(false);
            userRepository.save(user);
        }
    }
}
