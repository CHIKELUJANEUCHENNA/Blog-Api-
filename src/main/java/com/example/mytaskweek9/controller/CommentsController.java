package com.example.mytaskweek9.controller;

import com.example.mytaskweek9.model.Comments;
import com.example.mytaskweek9.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api",produces = "application/json")
public class CommentsController {
    private final CommentService commentService;

    @Autowired
    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/allComments")
    private ResponseEntity<List<Comments>> allComments () {
        List<Comments> commentsList = commentService.getAllComments();
        return new ResponseEntity<>(commentsList, HttpStatus.OK);
    }

    @PostMapping("/saveComment/{id}/{userId}")
    public ResponseEntity<Comments> saveComment(@Valid @RequestBody String message, @PathVariable Long id, @PathVariable Long userId) {
//        User user = userServices.getById(id);
        Comments comments1 = commentService.saveComment(message, id, userId);
        return new ResponseEntity<>(comments1, HttpStatus.CREATED);
    }

//    @PutMapping("/updateComment/{id}/{postId}")
//    private ResponseEntity<Comments> updateComment(@PathVariable Long id, @RequestBody String comments, Long userId, Long postId) {
//        Comments updateComment = commentService.updateComment(id, comments, userId, postId);
//        return new ResponseEntity<>(updateComment, HttpStatus.OK);
//    }

    @DeleteMapping("/deleteComment/{id}/{userId}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id, @PathVariable Long userId) {
        commentService.deleteComment(id, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
