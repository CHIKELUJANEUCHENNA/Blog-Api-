package com.example.mytaskweek9.repository;

import com.example.mytaskweek9.model.Comments;
import com.example.mytaskweek9.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {
    List<Comments> findCommentsByUser(User user);
}
