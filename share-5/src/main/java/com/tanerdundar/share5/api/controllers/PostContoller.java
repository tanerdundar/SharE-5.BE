package com.tanerdundar.share5.api.controllers;

import com.tanerdundar.share5.api.dto.GetOnePostByPostId;
import com.tanerdundar.share5.entities.Post;
import com.tanerdundar.share5.entities.User;
import com.tanerdundar.share5.requests.post.PostCreateRequest;
import com.tanerdundar.share5.service.abstracts.PostService;
import com.tanerdundar.share5.service.concrete.PostManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
public class PostContoller {

    private final PostService service;
    private final PostManager manager;

//    @PostMapping("/{userId}")
//    public Post createPost(@RequestBody PostCreateRequest request, User user) {
//        return service.createOnePost(request,user);
//    }
//
//    @GetMapping("/{postId}")
//    public GetOnePostByPostId getOnePostByPostId (@PathVariable Long postId){
//        return service.getOnePostById(postId);
//    }
//
//    @GetMapping
//    public List<Post> getAllPosts() {
//
//        return service.getAllPosts();
//    }
//
//    @GetMapping("/{userId}/posts")
//    public List<Post> getPostsByUserId(@PathVariable Long userId) {
//        return manager.getPostsByUserId(userId);
//    }

}
