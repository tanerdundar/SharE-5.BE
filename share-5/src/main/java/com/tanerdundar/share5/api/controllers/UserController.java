package com.tanerdundar.share5.api.controllers;

import com.tanerdundar.share5.entities.Post;
import com.tanerdundar.share5.entities.User;
import com.tanerdundar.share5.requests.user.UserCreateRequest;
import com.tanerdundar.share5.requests.user.UserDeleteRequest;
import com.tanerdundar.share5.requests.user.UserFollowRequest;
import com.tanerdundar.share5.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public User createOneUser(@RequestBody UserCreateRequest request) {
        return service.createOneUser(request);
    }

    @PutMapping("/{userId}")
    public User deleteOneUserById(@PathVariable Long userId, @RequestBody UserDeleteRequest request) {
        return service.deleteOneUserById(userId,request);
    }

    @GetMapping("/all")
    public List<User> getAllActiveUsers() {
         return service.getAllActiveUsers();
    }
    @GetMapping("/whole")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }
    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId){
        return service.getOneUserById(userId);
    }




    //------------------------------------------------------------------------------------------------------------------------------
    @GetMapping("/{userId}/followings/posts")
    public List<Post> getFollowingsPosts(@PathVariable long userId) {
        return service.getFollowingsPosts(userId);
    }

    //------------------------------------------------------------------------------------------------------------------------------

}
