package com.tanerdundar.share5.service.abstracts;

import com.tanerdundar.share5.api.dto.GetOnePostByPostId;
import com.tanerdundar.share5.entities.Post;
import com.tanerdundar.share5.entities.User;
import com.tanerdundar.share5.requests.post.PostCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    Post createOnePost(PostCreateRequest request, User user);
    GetOnePostByPostId getOnePostById(Long postId);

    List<Post> getAllPosts();

    List<Post> getPostsByUserId(Long userId);
}
