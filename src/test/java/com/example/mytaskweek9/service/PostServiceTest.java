package com.example.mytaskweek9.service;

import com.example.mytaskweek9.model.Post;
import com.example.mytaskweek9.model.User;
import com.example.mytaskweek9.repository.PostRepository;
import com.example.mytaskweek9.service.implementation.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PostServiceTest {
    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postService;

    private List<Post> allPosts;

    @BeforeEach
    void setUp() {
        this.allPosts = new ArrayList<>();
        this.allPosts.add(new Post(1L, "user one making a post", "posting"));
        this.allPosts.add(new Post(2L, "user one still making a post", "post"));
        this.allPosts.add(new Post(4L, "user two still making a post ooooo", "like"));
    }

//    @Test
//    void getAllPostedPost() {
////        given(postRepository.save(allPosts)).willReturn(allPosts);
//        given(postRepository.findAll()).willReturn(allPosts);
//        List<Post> expected = postService.getAllPost();
//        assertEquals(expected, allPosts);
//    }

//    @Test
//    void savePost() {
//        final Post post = new Post (null, "this a post", "isPost");
//        long userId = 1L;
//        User user = new User(userId, "jay jay", "jay@gmail.com", "09088776655", "11111");
////        given(postRepository.save(post)).willReturn(post);
//        post.setPost_Title("this a post");
//        post.setPost_messages("isPost");
//        Post post1 = postService.savePost(post.getPost_messages(), user, post.getPost_Title());
//        assertThat(post1).isNotNull();
//        assertThat(post1).isEqualTo(post);
//
//    }

    @Test
    void getAPost() {
    }

    @Test
    void updatePost() {
    }

    @Test
    void deletePost() {
    }
}