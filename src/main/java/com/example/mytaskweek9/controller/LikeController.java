package com.example.mytaskweek9.controller;

import com.example.mytaskweek9.model.Likes;
import com.example.mytaskweek9.service.CommentService;
import com.example.mytaskweek9.service.LikesService;
import com.example.mytaskweek9.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api",produces = "application/json")
public class LikeController {
    private final LikesService likesService;
    @Autowired
    public LikeController(LikesService likesService) {
        this.likesService = likesService;
    }

    @PostMapping("/likeAndUnlikePost/{userId}/{postId}")
    private ResponseEntity<String> likeAndUnlikePost(@PathVariable("userId") Long userId, @PathVariable("postId")Long postId){
        Likes likes = likesService.likeAndUnlikePost(userId, postId);
        HttpHeaders httpHeaders = new HttpHeaders();
        if(likes!=null){
            httpHeaders.add("Information","Successful");
            return new ResponseEntity<>("The post has been liked", httpHeaders, HttpStatus.ACCEPTED);
        }else
            httpHeaders.add("Information","Failed");
            return new ResponseEntity<>("The post has been unliked", httpHeaders, HttpStatus.EXPECTATION_FAILED);

//        return new ResponseEntity(likes, HttpStatus.OK);
    }

    @PostMapping("/likeAndUnlikeComment/{userId}/{commentId}")
    private ResponseEntity<String> likeAndUnlikeComment(@PathVariable("userId") Long userId, @PathVariable("commentId") Long commentId) {
        Likes likes = likesService.likeAndUnlikeComment(userId, commentId);
        HttpHeaders httpHeaders = new HttpHeaders();
        if(likes!=null){
            httpHeaders.add("Information","Successful");
            return new ResponseEntity<>("The comment has been liked", httpHeaders, HttpStatus.ACCEPTED);
        }else {
            httpHeaders.add("Information","Failed");
            return new ResponseEntity<>("The comment has been unliked", httpHeaders, HttpStatus.EXPECTATION_FAILED);
        }
//        return new ResponseEntity<>(likes, HttpStatus.OK);
    }
}
