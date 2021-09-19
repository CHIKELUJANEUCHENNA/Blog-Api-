package com.example.mytaskweek9.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;
    @NotNull
    private String fullName;
    @NotNull
    private String email;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String password;
    private Boolean flag;
    @OneToMany
    private List<Post> postList = new ArrayList<>();
    @JsonIgnore
    @OneToOne
    private Connection connection;

    public User(Long id, String fullName, String email, String phoneNumber, String password) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public User(Boolean flag) {
        this.flag = isFlag();
    }


    public boolean isFlag() {
        return true;
    }

//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
//    private List<Comments> commentsList = new ArrayList<>();
}
