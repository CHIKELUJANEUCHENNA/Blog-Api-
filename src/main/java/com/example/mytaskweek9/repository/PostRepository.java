package com.example.mytaskweek9.repository;

import com.example.mytaskweek9.model.Comments;
import com.example.mytaskweek9.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findPostByComments(Comments comments);
}
