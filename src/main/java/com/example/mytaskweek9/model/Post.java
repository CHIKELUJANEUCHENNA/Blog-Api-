package com.example.mytaskweek9.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Long post_id;
    private Date createdAt;
    @Transient
    private Timestamp timestamp;
    @NotBlank
    private String post_messages;
    private String post_Title;
//    @JsonIgnore
    @OneToMany
    private List<Comments> comments = new ArrayList<>();

    public Post(Long post_id, String post_messages, String post_Title) {
        this.post_id = post_id;
        this.post_messages = post_messages;
        this.post_Title = post_Title;
    }
}
