//package com.example.mytaskweek9.controller;
//
//import com.example.mytaskweek9.model.Post;
//import com.example.mytaskweek9.model.User;
//import com.example.mytaskweek9.service.PostService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(controllers = PostController.class)
//@ActiveProfiles("test")
//class PostControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private PostService postService;
//
//    private List<Post> allPosts;
//
//    @BeforeEach
//    void setUp() {
//        this.allPosts = new ArrayList<>();
//        this.allPosts.add(new Post(1L, "user one making a post", "posting"));
//        this.allPosts.add(new Post(2L, "user one still making a post", "post"));
//        this.allPosts.add(new Post(4L, "user two still making a post ooooo", "like"));
//    }
//
//
//    @Test
//    void fetchAllPosts() throws Exception {
//        Mockito.when(postService.getAllPost()).thenReturn(allPosts);
//        this.mockMvc.perform(get("/api/allPost"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.size()", is(allPosts.size())));
//    }
//}