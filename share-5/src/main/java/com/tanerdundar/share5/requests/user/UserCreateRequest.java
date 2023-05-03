package com.tanerdundar.share5.requests.user;

import com.tanerdundar.share5.entities.Post;
import com.tanerdundar.share5.entities.Statu;
import com.tanerdundar.share5.entities.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class UserCreateRequest {

    private String userName;
    private String eMail;
    private String password;

    public User createOneUser() {
        User newUser = new User();
        newUser.setUserName(this.userName);
        newUser.setEMail(this.eMail);
        newUser.setPassword(this.password);
        return newUser;
    }

}
