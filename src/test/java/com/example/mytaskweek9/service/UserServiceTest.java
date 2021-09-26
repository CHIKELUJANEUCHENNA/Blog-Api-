package com.example.mytaskweek9.service;

import com.example.mytaskweek9.dto.SignUpDto;
import com.example.mytaskweek9.exceptions.AppException;
import com.example.mytaskweek9.exceptions.UserRegistrationException;
import com.example.mytaskweek9.model.User;
import com.example.mytaskweek9.repository.UserRepository;
import com.example.mytaskweek9.service.implementation.UserServicesImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServicesImpl userServices;



    @MockitoSettings(strictness = Strictness.LENIENT)
    @Test
    void shouldSavedUserSuccessfully() {
        SignUpDto signUpDto = new SignUpDto("agnes agnes", "agnes@gmail.com", "09088776655", "00000");
        User user = new User();
        user.setFullName(signUpDto.getFullName());
        user.setEmail(signUpDto.getEmail());
        user.setPhoneNumber(signUpDto.getPhoneNumber());
        user.setPassword(signUpDto.getPassword());

        when(userRepository.save(user)).then(invocation -> invocation.getArgument(0));
        ResponseEntity<?> savedUser = userServices.signUp(signUpDto);

        assertThat(savedUser).isNotNull();
//        assertThat(savedUser).isEqualTo(user);
        verify(userRepository).save(any(User.class));
    }

//    @MockitoSettings(strictness = Strictness.LENIENT)
//    @Test
//    void shouldThrowErrorWhenSaveUserWithExistingEmail() {
//        SignUpDto signUpDto = new SignUpDto("agnes agnes", "agnes@gmail.com", "09088776655", "00000");
//        User user = new User();
//        user.setFullName(signUpDto.getFullName());
//        user.setEmail(signUpDto.getEmail());
//        user.setPhoneNumber(signUpDto.getPhoneNumber());
//        user.setPassword(signUpDto.getPassword());
//        when(userRepository.save(user)).then(invocation -> invocation.getArgument(0));
//
//        assertThrows(UserRegistrationException.class,() -> {
//            userServices.signUp(signUpDto);
//        });
//
//        verify(userRepository, never()).save(any(User.class));
//    }


    @Test
    void showAllRegisteredUser() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(5L, "peace peace", "pee@gmail.com", "09088776655", "11111"));
        userList.add(new User(6L, "sam love", "sam@gmail.com", "09088776655", "11111"));
        userList.add(new User(1L, "jay jay", "jay@gmail.com", "09088776655", "11111"));
        userList.add(new User(7L, "peace love", "love@gmail.com", "09088776655", "22222"));
        userList.add(new User(8L, "new user", "new@gmail.com", "09088776655", "11111"));

        given(userRepository.findAll()).willReturn(userList);
        List<User> expected = userServices.getAllUser();
        assertEquals(expected, userList);
    }


    @Test
    void findUserById() {
        final Long id = 1L;
        final User user = new User(1L,"jay jay","jay@gmail.com","09088776655","11111");
        given(userRepository.findById(id)).willReturn(Optional.of(user));
        final Optional<User> expected = userServices.findUserById(id);
        assertThat(expected).isNotNull();


    }

    @MockitoSettings(strictness = Strictness.LENIENT)
    @Test
    void updateUser() {
        long userId = 1L;
        User user = new User(userId, "jay jay", "jay@gmail.com", "09088776655", "11111");
        given(userServices.findUserById(userId)).willReturn(Optional.of(user));
        given(userServices.updateUser(userId, user)).willAnswer((invocation) -> invocation.getArgument(0));
        User expected = userServices.findUserById(userId).get();
        assertThat(expected).isEqualTo(user);

    }


//    @Test
//    void deleteUser() {
//        final Long userId = 6L;
//        userServices.deleteUser(userId);
//        userServices.deleteUser(userId);
//        verify(userRepository, times(2)).deleteById(userId);
//    }

    @Test
    void cancelDelete() {
    }
}