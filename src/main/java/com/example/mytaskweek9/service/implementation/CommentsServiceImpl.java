package com.example.mytaskweek9.service.implementation;

import com.example.mytaskweek9.exceptions.AppException;
import com.example.mytaskweek9.model.*;
import com.example.mytaskweek9.repository.*;
import com.example.mytaskweek9.service.CommentService;
import com.example.mytaskweek9.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentsServiceImpl implements CommentService {
    private final PostService postService;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikesRepository likesRepository;
    private final FavouriteRepository favouriteRepository;

    public CommentsServiceImpl(PostService postService, CommentRepository commentRepository, UserRepository userRepository,
                               PostRepository postRepository, LikesRepository likesRepository, FavouriteRepository favouriteRepository) {
        this.postService = postService;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.likesRepository = likesRepository;
        this.favouriteRepository = favouriteRepository;
    }

    @Override
    public List<Comments> getAllComments() {
        return commentRepository.findAll();
    }


    @Override
    public Comments saveComment(String message, long id, long userId) {
        Date date = Date.valueOf(LocalDate.now());
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        if (message.isBlank()) {
            throw new AppException("Post can not be created", HttpStatus.BAD_REQUEST);
        } else {
            Post post = postService.getAPost(id);
//                    postRepository.findById(id);
            User user1 = userRepository.getById(userId);
            Comments comments = new Comments();
            comments.setComment_messages(message);
            comments.setUser(user1);
            comments.setCreatedAt(date);
            comments.setTimestamp(timestamp);
//            comments.setPost(post);
            List<Comments>commentsList= post.getComments();
            commentsList.add(comments);
            post.setComments(commentsList);
            commentRepository.save(comments);
            postRepository.save(post);
//            user.getCommentsList().add(comments1);
            userRepository.save(user1);
            return comments;

        }
    }


//    @Override
//    public Comments updateComment(long id, Comments comments) {
//        Optional<Comments> comments1 = commentRepository.findById(id);
//        if (comments1.isEmpty()) {
//            throw new AppException("Comment not found", HttpStatus.BAD_REQUEST);
//        }
//        else
//            comments1.get().setComment_messages(comments.getComment_messages());
////        comments1.get().setPost(comments.getPost());
//        commentRepository.save(comments1.get());
//        return comments1.get();
//    }

//    @Override
//    public Comments updateComment(long id, String comments, long userId, long postId) {
//        Optional<Comments> comments1 = commentRepository.findById(id);
//        if (comments1.isEmpty()) {
//            throw new AppException("Comment not found", HttpStatus.BAD_REQUEST);
//        }
//        User user = userRepository.getById(userId);
//        Optional<Post> post = postRepository.findById(postId);
//        if(user.getCommentsList().contains(comments1.get())) {
//            if(user.getPostList().contains(post.get())) {
//                comments1.get().setComment_messages(comments);
////                comments1.get().setUser(user);
//            }
//        }
//        commentRepository.save(comments1.get());
//        return comments1.get();
//    }


    @Override
    public void deleteComment(long id, long userId) {
        Comments comments = commentRepository.getById(id);
        System.out.println("comment "+comments.getComment_messages());
        if (comments.getUser().getId() == userId) {
//            List<Likes> likesList = likesRepository.findLikesByComments(comments);
//            while (likesList.size() > 0) {
//                Likes likes = likesList.get(0);
//                likesRepository.delete(likes);
//                likesList.remove(likes);
//            }
//            List<Post> postList = postRepository.findAll();
//            for (Post post : postList) {
//                if (post.getComments().contains(comments)) {
//                    System.out.println("-------Post "+post.getPost_messages());
//                    List<Favourite> favourite = favouriteRepository.findAll();
//                    for (Favourite favourite1 : favourite) {
//                        if (favourite1.getPosts().contains(post)) {
//                            List<Post> posts = favourite1.getPosts();
//                            List<Comments> commentsList = post.getComments();
//                            posts.remove(post);
//                            boolean status = commentsList.remove(comments);
//                            if (status) {
//                                post.setComments(commentsList);
//                                postRepository.save(post);
//                                posts.add(post);
//                                favourite1.setPosts(posts);
//                                favouriteRepository.save(favourite1);
//                                System.out.println("successful");
//                            } else {
//                                System.out.println("failed");
//                            }
//                        } else {
//                            System.out.println("not found");
//                            List<Comments> commentsList = post.getComments();
//                            commentsList.remove(comments);
//                            post.setComments(commentsList);
//                            postRepository.save(post);
//                        }
//                    }
//                }
//            }

//        likesRepository.deleteAllByComments(comments);
            commentRepository.delete(comments);
        }
    }
}
