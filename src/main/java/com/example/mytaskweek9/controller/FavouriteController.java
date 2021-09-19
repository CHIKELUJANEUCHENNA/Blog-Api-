package com.example.mytaskweek9.controller;

import com.example.mytaskweek9.model.Favourite;
import com.example.mytaskweek9.model.Post;
import com.example.mytaskweek9.service.FavouriteService;
import com.example.mytaskweek9.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FavouriteController {
    private final PostService postService;
    private final FavouriteService favouriteService;

    @Autowired
    public FavouriteController(PostService postService, FavouriteService favouriteService) {
        this.postService = postService;
        this.favouriteService = favouriteService;
    }

    @GetMapping("/getAllFavouritePost/{userId}")
    private ResponseEntity<List<Post>> getAllFavouritePost (@PathVariable Long userId) {
        List<Post> favouritePostList = favouriteService.getAllFavouritePostByUser(userId);
        return new ResponseEntity<>(favouritePostList, HttpStatus.OK);
    }

    @PostMapping("/addPostToFavourite/{postId}/{userId}")
    public ResponseEntity<Favourite> addPostToFavourite(@Valid @PathVariable(name="postId") Long postId,@PathVariable(name="userId") Long userId) {
        Favourite favourite1 = favouriteService.addPostToFavourite(postId, userId);
        return new ResponseEntity<>(favourite1, HttpStatus.OK);
    }

//    @GetMapping("/getAFavouritePost/{id}")
//    private ResponseEntity<Favourite> getAFavouritePost(@PathVariable("id") Long id){
//        Favourite favourite = favouriteService.getAFavouritePost(id);
//        return new ResponseEntity<>(favourite, HttpStatus.OK);
//    }

    @DeleteMapping("/deleteFavouritePost/{id}")
    public ResponseEntity<Favourite> deleteFavouritePost(@PathVariable("id") Long id) {
        favouriteService.deleteFavouritePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
