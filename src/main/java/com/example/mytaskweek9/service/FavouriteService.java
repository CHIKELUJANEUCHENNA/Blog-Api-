package com.example.mytaskweek9.service;

import com.example.mytaskweek9.model.Favourite;
import com.example.mytaskweek9.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FavouriteService {
    List<Post> getAllFavouritePostByUser(Long userId);
    Favourite addPostToFavourite(Long postId, Long userId);
    void deleteFavouritePost(long id);
}
