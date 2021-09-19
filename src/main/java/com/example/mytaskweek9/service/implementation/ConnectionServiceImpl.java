package com.example.mytaskweek9.service.implementation;

import com.example.mytaskweek9.model.Connection;
import com.example.mytaskweek9.model.User;
import com.example.mytaskweek9.repository.ConnectionRepository;
import com.example.mytaskweek9.repository.UserRepository;
import com.example.mytaskweek9.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConnectionServiceImpl implements ConnectionService {
    private final ConnectionRepository connectionRepository;
    private final UserRepository userRepository;

    @Autowired
    public ConnectionServiceImpl(ConnectionRepository connectionRepository, UserRepository userRepository) {
        this.connectionRepository = connectionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<User> listOfMyConnections(Long userId) {
        return userRepository.getById(userId).getConnection().getUserConnections();
    }

    @Override
    public Connection addUserToMyConnection(Long userId_1, Long userId_2) {
        User user1 = userRepository.getById(userId_1);
        User user = userRepository.getById(userId_2);
        Connection connection;

        if (user1.getConnection() == null) {
            connection = new Connection();
            List<User> connections = new ArrayList<>();
            connections.add(user);
            connection.setUserConnections(connections);
            user1.setConnection(connection);
            connectionRepository.save(connection);
        } else {
            connection = user1.getConnection();
            connection.getUserConnections().add(user);
            user1.setConnection(connection);
        }
        if (user.getConnection() == null) {
            connection = new Connection();
            List<User> connections = new ArrayList<>();
            connections.add(user1);
            connection.setUserConnections(connections);
            user.setConnection(connection);
            connectionRepository.save(connection);
        } else {
            connection = user.getConnection();
            connection.getUserConnections().add(user1);
            user.setConnection(connection);
        }
        userRepository.save(user);
        userRepository.save(user1);
        return connection;
    }
}
