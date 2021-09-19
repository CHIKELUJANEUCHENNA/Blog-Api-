package com.example.mytaskweek9.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comments_id", nullable = false)
    private Long comments_id;
    private Date createdAt;
    @Transient
    private Timestamp timestamp;
    private String comment_messages;
//    @JsonIgnore
//    @ManyToOne//(cascade = CascadeType.REMOVE)
//   // @JoinColumn(name = "comments_post_id")
//    private Post post;
    @JsonIgnore
    @ManyToOne
    private User user;

//    @OneToMany
//    private List<Likes> likes;

}
