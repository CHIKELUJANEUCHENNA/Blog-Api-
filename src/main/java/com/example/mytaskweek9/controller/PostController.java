package com.example.mytaskweek9.controller;

import com.example.mytaskweek9.model.Post;
import com.example.mytaskweek9.model.User;
import com.example.mytaskweek9.repository.UserRepository;
import com.example.mytaskweek9.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api",produces = "application/json")
public class PostController {
    private final UserRepository userService;
    private final PostService postService;

    @Autowired
    public PostController(UserRepository userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/allPost")
    private ResponseEntity<List<Post>> allPost () {
        List<Post> posts = postService.getAllPost();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    @PostMapping("/savePost/{id}")
    public Post savePost(@Valid @RequestBody String message, @PathVariable Long id, @RequestParam String post_Title) {
        User user = userService.getById(id);
        return postService.savePost(message, user, post_Title);
    }

    @GetMapping("/findPost/{id}")
    private ResponseEntity<Post> findPost(@PathVariable("id") Long id){
        Post post = postService.getAPost(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping("/updatePost/{id}/{userId}")
    private ResponseEntity<Post> updatePost(@PathVariable("id") Long id, @RequestBody String message, @PathVariable("userId") Long userId, @RequestParam String post_Title) {
        Post updatePost = postService.updatePost(id, message, userId, post_Title);
        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}/{userId}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        postService.deletePost(id, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
