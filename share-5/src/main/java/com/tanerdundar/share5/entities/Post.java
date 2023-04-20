package com.tanerdundar.share5.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="posts")
@Data
public class Post {

    @Id
    @Column(name="post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;

    @Column (name="content")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column (name="post_statu")
    private Statu postStatu;

    @Column(name="number_of_likes")
    private int numberOfLikes;

    @ManyToOne
    private User owner;

}
