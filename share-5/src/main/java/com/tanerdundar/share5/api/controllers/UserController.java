package com.tanerdundar.share5.api.controllers;

import com.tanerdundar.share5.entities.User;
import com.tanerdundar.share5.requests.user.UserCreateRequest;
import com.tanerdundar.share5.service.abstracts.FollowService;
import com.tanerdundar.share5.service.abstracts.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final FollowService followService;

    @GetMapping("/{userId}")
    public ResponseEntity getOneUserByUserId(@PathVariable long userId) {
        User user = userService.getOneUserByUserId(userId);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/{userId}/active")
    public ResponseEntity getOneActiveUserByUserId(@PathVariable long userId) {
        User user = userService.getOneActiveUserByUserId(userId);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/{postId}/post")
    public ResponseEntity getOneUserByPostId(@PathVariable long postId) {
        User user = userService.getOneUserByPostId(postId);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/{userId}/followings")
    public ResponseEntity getAllFollowingsByUserId(@PathVariable long userId) {
        List<User> followings = userService.getFollowingsByUserId(userId);
        return ResponseEntity.ok(followings);
    }
    @GetMapping("/{userId}/followers")
    public ResponseEntity getAllFollowersByUserId(@PathVariable long userId) {
        List<User> followers = userService.getFollowersByUserId(userId);
        return ResponseEntity.ok(followers);
    }
    @GetMapping("/actives")
    public ResponseEntity getAllActiveUsers(){
        List<User> allActiveUsers= userService.getAllActiveUsers();
        return ResponseEntity.ok(allActiveUsers);
    }
    @GetMapping
    public ResponseEntity getAllUsers(){
        List<User> allUsers= userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
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
//    @PutMapping("/{userId}")
//    public ResponseEntity toInactiveAUser(@PathVariable long userId, @RequestBody  UserToInactiveRequest request){
//        User user = userService.toInactiveAUser(userId,request);
//        return ResponseEntity.ok(user);
//    }


}
