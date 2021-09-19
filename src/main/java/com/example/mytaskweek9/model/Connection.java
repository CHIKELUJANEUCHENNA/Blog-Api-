package com.example.mytaskweek9.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    private List<User> userConnections;
}
