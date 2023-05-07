package com.tanerdundar.share5.api.controllers;

import com.tanerdundar.share5.entities.Post;
import com.tanerdundar.share5.entities.Statu;
import com.tanerdundar.share5.entities.User;
import com.tanerdundar.share5.requests.post.PostCreateRequest;
import com.tanerdundar.share5.service.abstracts.PostService;
import com.tanerdundar.share5.service.concrete.PostManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{postId}")
    public ResponseEntity getOnePostByPostId(@PathVariable long postId) {
        Post post = postService.getOnePostByPostId(postId);
        return ResponseEntity.ok(post);
    }
    @GetMapping("/{postId}/active")
    public ResponseEntity getOneActivPostByPostId(@PathVariable long postId) {
        Post post = postService.getOneActivePostByPostId(postId);
        return ResponseEntity.ok(post);
    }

    @GetMapping
    public ResponseEntity getAllPosts() {
        List<Post> allPosts =postService.getAllPosts();
        return ResponseEntity.ok(allPosts);
    }
    @GetMapping("/actives")
    public ResponseEntity getAllActivePosts(){
        List<Post> allActivePosts= postService.getAllActivePosts();
        return ResponseEntity.ok(allActivePosts);
    }
    @GetMapping("/{userId}/posts")
    public ResponseEntity findAllByOwnerUserId(@PathVariable long userId){
        List<Post> posts = postService.findAllByOwnerUserId(userId);
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/{userId}/actives")
    public ResponseEntity findAllByOwner_UserIdAndPostStatu(@PathVariable long userId, Statu statu) {
        List<Post> posts = postService.findAllByOwner_UserIdAndPostStatu(userId,statu);
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/{userId}/following_posts")
    public ResponseEntity getAllFollowingsPosts(@PathVariable long userId) {
        List<Post> followingsPosts = postService.getAllFollowingsPosts(userId);
        return ResponseEntity.ok(followingsPosts);
    }
    @GetMapping("/{userId}/home")
    public ResponseEntity getAllFollowingsActivePosts(@PathVariable long userId) {
        List<Post> followingsPosts = postService.getAllFollowingsActivePosts(userId);
        return ResponseEntity.ok(followingsPosts);
    }
    @PostMapping("/{userId}")
    public ResponseEntity createOnePost(@Valid @RequestBody PostCreateRequest request, @PathVariable long userId) {
        Post post = postService.createOnePost(request,userId);
        return ResponseEntity.ok(post);
    }
}
