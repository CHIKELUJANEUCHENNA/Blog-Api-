package com.example.mytaskweek9.service;

import com.example.mytaskweek9.model.Likes;
import org.springframework.stereotype.Service;

@Service
public interface LikesService {
    Likes likeAndUnlikePost(long userId, long postId);
    Likes likeAndUnlikeComment(long userId,long commentId);
}
