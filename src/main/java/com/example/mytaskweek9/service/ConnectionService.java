package com.example.mytaskweek9.service;

import com.example.mytaskweek9.model.Connection;
import com.example.mytaskweek9.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ConnectionService {
    List<User> listOfMyConnections (Long userId);
    Connection addUserToMyConnection(Long userId_1, Long userId_2);

}
