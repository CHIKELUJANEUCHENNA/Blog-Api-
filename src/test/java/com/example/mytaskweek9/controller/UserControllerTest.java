package com.example.mytaskweek9.controller;

import com.example.mytaskweek9.model.User;
import com.example.mytaskweek9.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@ActiveProfiles("test")

class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<User> allUsers;

    @BeforeEach
    void setUp() {
        this.allUsers = new ArrayList<>();
        this.allUsers.add(new User(1L, "jay jay", "jay@gmail.com", "09088776655", "11111"));
        this.allUsers.add(new User(5L, "peace peace", "pee@gmail.com", "09088776655", "11111"));
        this.allUsers.add(new User(6L, "sam love", "sam@gmail.com", "09088776655", "11111"));
    }

    @Test
    void fetchAllUsers() throws Exception {
        Mockito.when(userService.getAllUser()).thenReturn(allUsers);
        this.mockMvc.perform(get("/api/allUsers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(allUsers.size())));
    }

//    @Test
//    void shouldUpdateUser() throws Exception {
//        long userId = 1L;
//        User user = new User(userId, "jay jay", "jay@gmail.com", "09088776655", "11111");
//        given(userService.findUserById(userId)).willReturn(Optional.of(user));
//        given(userService.updateUser(userId, user)).willAnswer((invocation) -> invocation.getArgument(0));
//
//        this.mockMvc.perform(put("/api/updateUser/{id}", user.getId())
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(objectMapper.writeValueAsString(user)))
//
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].fullName").value("jay jay"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("jay@gmail.com"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].phoneNumber").value("09088776655"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].password").value("11111"));

//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].fullName", is(user.getFullName())))
//                .andExpect(jsonPath("$[0].email", is(user.getEmail())))
//                .andExpect(jsonPath("$[0].phoneNumber", is(user.getPhoneNumber())))
//                .andExpect(jsonPath("$[0].password", is(user.getPassword())));
//    }


}