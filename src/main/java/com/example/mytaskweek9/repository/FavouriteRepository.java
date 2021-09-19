package com.example.mytaskweek9.repository;

import com.example.mytaskweek9.model.Favourite;
import com.example.mytaskweek9.model.Post;
import com.example.mytaskweek9.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    Optional<Favourite> findFavouriteByUser(User user);
    Favourite findFavouriteByPosts(Post post);
    void deleteById(Favourite favourite);
}
