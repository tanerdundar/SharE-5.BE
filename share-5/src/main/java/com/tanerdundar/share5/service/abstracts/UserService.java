package com.tanerdundar.share5.service.abstracts;

import com.tanerdundar.share5.entities.User;
import com.tanerdundar.share5.requests.user.UserCreateRequest;
import com.tanerdundar.share5.requests.user.UserDeleteRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User createOneUser(UserCreateRequest request);

    User deleteOneUserById(Long userId, UserDeleteRequest request);

    List<User> getAllActiveUsers();

    List<User> getAllUsers();

    User getOneUserById(Long userId);

}
