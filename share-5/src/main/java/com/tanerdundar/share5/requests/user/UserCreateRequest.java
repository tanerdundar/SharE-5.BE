package com.tanerdundar.share5.requests.user;

import com.tanerdundar.share5.entities.Post;
import com.tanerdundar.share5.entities.Statu;
import com.tanerdundar.share5.entities.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class UserCreateRequest {


    private long userId;
    private String userName;
    private String profileName;
    private String eMail;
    private String password;
    private Statu userStatu;
    private List<Post> posts;
    private  List<User> followers;
    private List<User> followings;
}
