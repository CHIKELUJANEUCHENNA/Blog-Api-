package com.example.mytaskweek9.service;

import com.example.mytaskweek9.model.Comments;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CommentService {
    List<Comments> getAllComments();
    Comments saveComment(String message, long id, long userId);
//    Comments getAComment(long id);
//    Comments updateComment(long id, String comments, long userId, long postId);
    void deleteComment(long id, long userId);
}
