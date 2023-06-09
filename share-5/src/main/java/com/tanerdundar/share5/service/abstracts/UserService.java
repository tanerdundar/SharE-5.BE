package com.tanerdundar.share5.service.abstracts;

import com.tanerdundar.share5.entities.User;
import com.tanerdundar.share5.requests.user.UserCreateRequest;
import com.tanerdundar.share5.requests.user.UserUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User getOneUserByUserId(long userId);

    User getOneActiveUserByUserId(long userId);

    User getOneUserByPostId(long postId);

    User getOneActiveUserByPostId(long postId);

    User getOneUserByFollowIdAsFollower(long followId);

    User getOneActiveUserByFollowIdAsFollower(long followId);

    User getOneUserByFollowIdAsFollowing(long followId);

    User getOneActiveUserByFollowIdAsFollowing(long followId);

    List<User> getAllUsers();

    List<User> getAllActiveUsers();

    List<User> getAllFollowersByUserId(long userId);
    List<User> getAllActiveFollowersByUserId(long userId);

    List<User> getAllFollowingsByUserId(long userId);
    List<User> getAllActiveFollowingsByUserId(long userId);
    User createOneUser(UserCreateRequest request);

    void deleteOneUserByUserIdFromDB(long userId);


    User deleteOneUserByUserId(long userId);

}
