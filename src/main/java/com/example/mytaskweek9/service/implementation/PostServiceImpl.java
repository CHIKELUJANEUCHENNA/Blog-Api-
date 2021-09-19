package com.example.mytaskweek9.service.implementation;

import com.example.mytaskweek9.exceptions.AppException;
import com.example.mytaskweek9.model.Post;
import com.example.mytaskweek9.model.User;
import com.example.mytaskweek9.repository.PostRepository;
import com.example.mytaskweek9.repository.UserRepository;
import com.example.mytaskweek9.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post savePost(String message, User user, String post_Title) {
        Date date = Date.valueOf(LocalDate.now());
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        if(message.isBlank()) {
            throw new AppException("Post can not be created", HttpStatus.BAD_REQUEST);
        } else {
            Post post = new Post();
            post.setPost_messages(message);
            post.setPost_Title(post_Title);
            post.setCreatedAt(date);
            post.setTimestamp(timestamp);
            Post post1 = postRepository.save(post);
            user.getPostList().add(post1);
            userRepository.save(user);
            return post1;

        }
    }

    @Override
    public Post getAPost(long id) {
        Optional<Post> post1 = postRepository.findById(id);
        if(!post1.isPresent()) {
            throw new RuntimeException("Post not found::" + id);
        } else
        return postRepository.findById(id).get();
    }


    @Override
    public Post updatePost(long id, String message, long userId, String post_Title) {
        Optional<Post> post1 = postRepository.findById(id);
        Date date = Date.valueOf(LocalDate.now());
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        if(post1.isEmpty()) {
            throw new AppException("Post not found", HttpStatus.BAD_REQUEST);
        }
        User user = userRepository.getById(userId);
        if(user.getPostList().contains(post1.get())) {
            post1.get().setPost_messages(message);
            post1.get().setCreatedAt(date);
            post1.get().setTimestamp(timestamp);
            post1.get().setPost_Title(post_Title);
            postRepository.save(post1.get());
        } else {
            throw new AppException("Post can not be updated", HttpStatus.BAD_REQUEST);
        }
        return post1.get();
    }


    @Override
    public void deletePost(long id, long userId) {
        User user = userRepository.getById(userId);
        Optional<Post> post1 = postRepository.findById(id);
        if(user.getPostList().contains(post1.get())) {
            this.postRepository.deleteById(id);
        } else {
            throw new AppException("Post can not be deleted", HttpStatus.BAD_REQUEST);
        }
    }

}
