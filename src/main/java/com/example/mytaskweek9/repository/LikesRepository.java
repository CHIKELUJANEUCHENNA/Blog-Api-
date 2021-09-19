package com.example.mytaskweek9.repository;

import com.example.mytaskweek9.model.Comments;
import com.example.mytaskweek9.model.Likes;
import com.example.mytaskweek9.model.Post;
import com.example.mytaskweek9.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findLikesByPostAndUser(Post post, User user);
    Optional<Likes> findLikesByCommentsAndUser(Comments comments, User user);
    List<Likes> deleteAllByComments(Comments comments);
    List<Likes> findLikesByUser(User user);
    List<Likes> findLikesByComments(Comments comments);

}
