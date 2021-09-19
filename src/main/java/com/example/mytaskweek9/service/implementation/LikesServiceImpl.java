package com.example.mytaskweek9.service.implementation;

import com.example.mytaskweek9.model.Comments;
import com.example.mytaskweek9.model.Likes;
import com.example.mytaskweek9.model.Post;
import com.example.mytaskweek9.model.User;
import com.example.mytaskweek9.repository.CommentRepository;
import com.example.mytaskweek9.repository.LikesRepository;
import com.example.mytaskweek9.repository.PostRepository;
import com.example.mytaskweek9.repository.UserRepository;
import com.example.mytaskweek9.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikesServiceImpl implements LikesService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikesRepository likesRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public LikesServiceImpl(PostRepository postRepository, UserRepository userRepository,
                            LikesRepository likesRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.likesRepository = likesRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Likes likeAndUnlikePost(long userId, long postId) {
        Post post = postRepository.getById(postId);
        User user = userRepository.getById(userId);
        Optional<Likes>like=likesRepository.findLikesByPostAndUser(post,user);
        Likes like1;
        if(like.isPresent()){
            like1 = like.get();
            likesRepository.delete(like1);
        } else {
            like1 = new Likes();
            like1.setPost(post);
            like1.setUser(user);
            likesRepository.save(like1);
        }
           return like1;

    }


    @Override
    public Likes likeAndUnlikeComment(long userId, long commentId) {
        Comments comments = commentRepository.getById(commentId);
        User user2 = userRepository.getById(userId);
        Optional<Likes>like=likesRepository.findLikesByCommentsAndUser(comments, user2);
        Likes like1;
        if(like.isPresent()){
            like1 = like.get();
            likesRepository.delete(like.get());
        } else {
            like1 = new Likes();
            like1.setComments(comments);
            like1.setUser(user2);
            likesRepository.save(like1);
        }
        return like1;
    }
}
