package com.tanerdundar.share5.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="post")
@Data
public class Post {

    @Id
    @Column(name="post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;

    @Column (name="content")
    private String content;

    @Column(name="number_of_likes")
    private int numberOfLikes;

    @ManyToOne
    private User owner;

}
