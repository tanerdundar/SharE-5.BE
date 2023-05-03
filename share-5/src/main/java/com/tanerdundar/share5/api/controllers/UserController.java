package com.tanerdundar.share5.api.controllers;

import com.tanerdundar.share5.entities.Post;
import com.tanerdundar.share5.entities.User;
import com.tanerdundar.share5.requests.user.UserCreateRequest;
import com.tanerdundar.share5.requests.user.UserDeleteRequest;
import com.tanerdundar.share5.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @PostMapping
//    public ResponseEntity createOneUser(@RequestBody UserCreateRequest request) {
//        User user = userService.createOneUser(request);
//        return ResponseEntity.ok(user);
//    }

    @GetMapping("/{userId}")
    public ResponseEntity getOneUserByUserId(@PathVariable long userId) {
        User user = userService.getOneUserByUserId(userId);
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

    //---------------------------------------------------------------------------------------------------------------------------------

//    @PostMapping
//    public User createOneUser(@RequestBody UserCreateRequest request) {
//        return service.createOneUser(request);
//    }
//
//    @PutMapping("/{userId}")
//    public User deleteOneUserById(@PathVariable Long userId, @RequestBody UserDeleteRequest request) {
//        return service.deleteOneUserById(userId,request);
//    }
//
//    @GetMapping("/all")
//    public List<User> getAllActiveUsers() {
//         return service.getAllActiveUsers();
//    }
//    @GetMapping("/whole")
//    public List<User> getAllUsers() {
//        return service.getAllUsers();
//    }
//    @GetMapping("/{userId}")
//    public User getOneUser(@PathVariable Long userId){
//        return service.getOneUserById(userId);
//    }




    //------------------------------------------------------------------------------------------------------------------------------
//    @GetMapping("/{userId}/followings/posts")
//    public List<Post> getFollowingsPosts(@PathVariable long userId) {
//        return service.getFollowingsPosts(userId);
//    }

    //------------------------------------------------------------------------------------------------------------------------------

}
