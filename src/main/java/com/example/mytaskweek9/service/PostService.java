package com.example.mytaskweek9.service;

import com.example.mytaskweek9.model.Post;
import com.example.mytaskweek9.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    List<Post> getAllPost();
    Post savePost(String message, User user, String post_Title);
    Post getAPost(long id);
//    Post likedPost(long id);
    Post updatePost(long id, String message, long userId, String post_Title);
    void deletePost(long id, long userId);
}
