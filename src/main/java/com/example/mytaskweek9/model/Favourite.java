package com.example.mytaskweek9.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Favourite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @JsonIgnore
    @OneToMany
    private List<Post> posts;
//    @JsonIgnore
    @OneToOne
    private User user;
}
