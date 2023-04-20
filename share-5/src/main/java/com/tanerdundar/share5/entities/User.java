package com.tanerdundar.share5.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table (name="users")
@Data
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(name="user_name")
    private String userName;

    @Column(name="profile_name")
    private String profileName;

    @Column(name="eMail")
    private String eMail;

    @Enumerated(EnumType.STRING)
    @Column (name="user_statu")
    private Statu userStatu;

    @OneToMany (mappedBy = "content")
    private List<Post> posts;

    @OneToMany (mappedBy = "userName")
    private  List<User> followers;

    @OneToMany (mappedBy = "userName")
    private List<User> followings;

}