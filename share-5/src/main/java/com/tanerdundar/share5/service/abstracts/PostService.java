package com.tanerdundar.share5.service.abstracts;

import com.tanerdundar.share5.entities.Post;
import com.tanerdundar.share5.entities.Statu;
import com.tanerdundar.share5.requests.post.PostCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    Post getOnePostByPostId(long postId);

    Post getOneActivePostByPostId(long postId);

    List<Post> getAllPosts();

    List<Post> getAllActivePosts();

    List<Post> getAllPostsByUserId(long userId);

    List<Post> getAllActivePostsByUserId(long userId);

    List<Post> getAllFollowingsPostsByUserId(long userId);

    List<Post> getAllFollowingsActivePosts(long userId);

    Post createOnePost(PostCreateRequest request, long userId);
    void deleteOnePostByPostIdFromDB(long postId);
}
