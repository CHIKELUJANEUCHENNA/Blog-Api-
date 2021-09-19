package com.example.mytaskweek9.service.implementation;

import com.example.mytaskweek9.model.Favourite;
import com.example.mytaskweek9.model.Post;
import com.example.mytaskweek9.model.User;
import com.example.mytaskweek9.repository.FavouriteRepository;
import com.example.mytaskweek9.repository.PostRepository;
import com.example.mytaskweek9.repository.UserRepository;
import com.example.mytaskweek9.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FavouriteServiceImpl implements FavouriteService {
    private final FavouriteRepository favouriteRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public FavouriteServiceImpl(FavouriteRepository favouriteRepository, PostRepository postRepository,
                                UserRepository userRepository) {
        this.favouriteRepository = favouriteRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Post> getAllFavouritePostByUser(Long userId) {
        return favouriteRepository.getById(userId).getPosts();
    }

    @Override
    public Favourite addPostToFavourite(Long postId,Long userId) {
        Post post = postRepository.getById(postId);
        User user = userRepository.getById(userId);
        Optional<Favourite> favourite1 = favouriteRepository.findFavouriteByUser(user);
        Favourite favourite = new Favourite();
        if (!favourite1.isPresent()) {
//            Favourite favourite = new Favourite();
            List<Post> posts = new ArrayList<>();
            posts.add(post);
            favourite.setPosts(posts);
            favourite.setUser(user);
            favouriteRepository.save(favourite);
        } else {
            favourite1.get().getPosts().add(post);
            favouriteRepository.save(favourite1.get());
//            throw new AppException("Post already added as a favourite", HttpStatus.BAD_REQUEST);
        }
        return favourite;
    }

    @Override
    public void deleteFavouritePost(long id) {
        User user = userRepository.getById(id);
        Favourite favourite = favouriteRepository.findFavouriteByUser(user).get();
        favouriteRepository.deleteById(favourite);
    }
}
