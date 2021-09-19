package com.example.mytaskweek9.controller;

import com.example.mytaskweek9.model.Connection;
import com.example.mytaskweek9.model.User;
import com.example.mytaskweek9.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api",produces = "application/json")
public class ConnectionController {
    private final ConnectionService connectionService;

    @Autowired
    public ConnectionController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @GetMapping("/listAllConnections/{userId}")
    private ResponseEntity<List<User>> listAllConnections (@PathVariable Long userId) {
        List<User> connections = connectionService.listOfMyConnections(userId);
        return new ResponseEntity<>(connections, HttpStatus.OK);
    }

    @PostMapping("/addUserToMyConnection/{userId_1}/{userId_2}")
    public ResponseEntity<Connection> addUserToMyConnection(@Valid @PathVariable Long userId_1, @PathVariable Long userId_2) {
        Connection connection = connectionService.addUserToMyConnection(userId_1, userId_2);
        return new ResponseEntity<>(connection, HttpStatus.OK);
    }
}
