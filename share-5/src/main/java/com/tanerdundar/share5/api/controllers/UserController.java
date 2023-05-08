package com.tanerdundar.share5.api.controllers;

import com.tanerdundar.share5.entities.User;
import com.tanerdundar.share5.requests.user.UserCreateRequest;
import com.tanerdundar.share5.requests.user.UserUpdateRequest;
import com.tanerdundar.share5.service.abstracts.FollowService;
import com.tanerdundar.share5.service.abstracts.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final FollowService followService;


    @GetMapping("/{userId}/inactive")
    public ResponseEntity getOneUserByUserId(@PathVariable long userId) {
        User user = userService.getOneUserByUserId(userId);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/{userId}")
    public ResponseEntity getOneActiveUserByUserId(@PathVariable long userId) {
        User user = userService.getOneActiveUserByUserId(userId);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/{postId}/user/inactive")
    public ResponseEntity getOneUserByPostId(@PathVariable long postId) {
        User user = userService.getOneUserByPostId(postId);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/{postId}/user")
    public ResponseEntity getOneActiveUserByPostId(@PathVariable long postId) {
        User user = userService.getOneActiveUserByPostId(postId);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/follower/{followId}/inactive")
    public ResponseEntity getOneUserByFollowIdAsFollower(@PathVariable long followId) {
        User user = userService.getOneUserByFollowIdAsFollower(followId);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/follower/{followId}")
    public ResponseEntity getOneActiveUserByFollowIdAsFollower(@PathVariable long followId) {
        User user = userService.getOneActiveUserByFollowIdAsFollower(followId);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/following/{followId}/inactive")
    public ResponseEntity getOneUserByFollowIdAsFollowing(@PathVariable long followId) {
        User user = userService.getOneUserByFollowIdAsFollowing(followId);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/following/{followId}")
    public ResponseEntity getOneActiveUserByFollowIdAsFollowing(@PathVariable long followId) {
        User user = userService.getOneActiveUserByFollowIdAsFollowing(followId);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/whole")
    public ResponseEntity getAllUsers(){
        List<User> allUsers= userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }
    @GetMapping("/all")
    public ResponseEntity getAllActiveUsers(){
        List<User> allActiveUsers= userService.getAllActiveUsers();
        return ResponseEntity.ok(allActiveUsers);
    }
    @GetMapping("/{userId}/followers/inactive")
    public ResponseEntity getAllFollowersByUserId(@PathVariable long userId) {
        List<User> followers = userService.getAllFollowersByUserId(userId);
        return ResponseEntity.ok(followers);
    }
    @GetMapping("/{userId}/followers")
    public ResponseEntity getAllActiveFollowersByUserId(@PathVariable long userId) {
        List<User> followers = userService.getAllActiveFollowersByUserId(userId);
        return ResponseEntity.ok(followers);
    }
    @GetMapping("/{userId}/followings/inactive")
    public ResponseEntity getAllFollowingsByUserId(@PathVariable long userId) {
        List<User> followings = userService.getAllFollowingsByUserId(userId);
        return ResponseEntity.ok(followings);
    }
    @GetMapping("/{userId}/followings")
    public ResponseEntity getAllActiveFollowingsByUserId(@PathVariable long userId ) {
        List<User> followings = userService.getAllActiveFollowingsByUserId(userId);
        return ResponseEntity.ok(followings);
    }
    @PostMapping
    public ResponseEntity createOneUser(@Valid @RequestBody UserCreateRequest request) {
       User user= userService.createOneUser(request);
        return ResponseEntity.ok(user);
    }
    @DeleteMapping("/{userId}")
    public void deleteOneUserByUserIdFromDB(@PathVariable long userId) {
        userService.deleteOneUserByUserIdFromDB(userId);
    }
    @PutMapping("/userId")
    public ResponseEntity deleteOneUserByUserId( @PathVariable  long userId) {
        User user = userService.deleteOneUserByUserId(userId);
        return ResponseEntity.ok(user);
    }
}
