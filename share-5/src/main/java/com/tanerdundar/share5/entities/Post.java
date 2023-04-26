package com.tanerdundar.share5.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name="posts")
@Data
@NoArgsConstructor
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
    @JoinColumn(name = "user_id", nullable = false)
    @NonNull
    private User owner;


    @PrePersist
    public void prePersist() {
        if(this.numberOfLikes>0){
            this.numberOfLikes=this.numberOfLikes;
        } else {
            this.numberOfLikes = 0;
        }

    }



}
