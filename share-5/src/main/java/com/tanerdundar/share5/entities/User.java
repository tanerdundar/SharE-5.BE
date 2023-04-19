package com.tanerdundar.share5.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table (name="user")
@Data
public class User {

    @Id
    @Column(name="book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(name="eMail")
    private String eMail;

    @OneToMany
    private List<Post> posts;

    @OneToMany
    private  List<User> followers;

    @OneToMany
    private List<User> followings;


}