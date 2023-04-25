package com.tanerdundar.share5.controllers;

import com.tanerdundar.share5.entities.Post;
import com.tanerdundar.share5.entities.User;
import com.tanerdundar.share5.requests.post.PostCreateRequest;
import com.tanerdundar.share5.service.abstracts.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
public class PostContoller {

    private final PostService service;

    @PostMapping("/{userId}")
    public Post createPost(@RequestBody PostCreateRequest request, User user) {
        return service.createOnePost(request,user);

    }

    @GetMapping("/{postId}")
    public Post getOnePostByPostId (@PathVariable Long postId){
        return service.getOnePostById(postId);
    }

    @GetMapping("/all/{userId}")
    public List<Post> getAllPostsOfOneUser(@PathVariable User user) {
        return  service.getPostsByUserId(user.getUserId());

    }

}
