package com.tanerdundar.share5.service.abstracts;

import com.tanerdundar.share5.entities.Post;
import com.tanerdundar.share5.entities.User;
import com.tanerdundar.share5.requests.user.UserCreateRequest;
import com.tanerdundar.share5.requests.user.UserFollowRequest;
import com.tanerdundar.share5.requests.user.UserToInactiveRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    User getOneUserByUserId(long userId);

    User getOneActiveUserByUserId(long userId);

    User getOneUserByPostId(long postId);

    List<User> getFollowingsByUserId(long userId);

    List<User> getFollowersByUserId(long userId);

    List<User> getAllActiveUsers();

    List<User> getAllUsers();

    User createOneUser(UserCreateRequest request);

    void deleteOneUserByUserIdFromDB(long userId);

 //   User toInactiveAUser(long userId, UserToInactiveRequest request);


}
