package com.tanerdundar.share5.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name="password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column (name="user_statu")
    private Statu userStatu;

    @JsonIgnore
    @OneToMany (mappedBy = "content")
    private List<Post> posts;

    @OneToMany (mappedBy = "userName")
    private  List<User> followers;

    @OneToMany (mappedBy = "userName")
    private List<User> followings;

    @PrePersist
    public void prePersist() {
        if(this.userStatu==Statu.INACTIVE) {
        }else {
            this.userStatu = Statu.ACTIVE;
        }
    }

}