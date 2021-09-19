package com.example.mytaskweek9.config;

import com.example.mytaskweek9.model.User;
import com.example.mytaskweek9.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class TaskScheduler {
    private UserRepository userRepository;

    @Autowired
    public TaskScheduler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Scheduled(fixedDelay = 180000)
    public void delete() {
        List<User> users = userRepository.findUserByFlag(true);
        userRepository.deleteAll(users);
    }
}
