package com.tanerdundar.share5.service.concrete;

import com.tanerdundar.share5.api.dto.GetOnePostByPostId;
import com.tanerdundar.share5.dao.PostRepository;
import com.tanerdundar.share5.dao.UserRepository;
import com.tanerdundar.share5.entities.Post;
import com.tanerdundar.share5.entities.User;
import com.tanerdundar.share5.requests.post.PostCreateRequest;
import com.tanerdundar.share5.service.abstracts.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostManager implements PostService {

    private final PostRepository repository;
    private final UserRepository userRepository;

    private final UserManager manager;

    @Override
    public Post createOnePost(PostCreateRequest request,User user) {
        User postOwner = manager.getOneUserById(user.getUserId());
        if (postOwner == null) {
            return null;
        }
        else{
            Post postToSave = new Post();
            postToSave.setContent(request.getContent());
            postToSave.setOwner(postOwner);
            postToSave.setPostStatu(postToSave.getOwner().getUserStatu());
//            postOwner.getPosts().add(postToSave);


            return repository.save(postToSave);

        }

    }

    @Override
    public GetOnePostByPostId getOnePostById(Long postId) {

        Post post = repository.findById(postId).orElse(null);
        GetOnePostByPostId myPost = new GetOnePostByPostId();
        myPost.setPostStatu(post.getPostStatu());
        myPost.setContent(post.getContent());
        myPost.setUserId(post.getOwner().getUserId());
        myPost.setContent(post.getContent());

        return myPost;
    }

    @Override
    public List<Post> getAllPosts() {
        return repository.findAll();
    }

    @Override
    public List<Post> getPostsByUserId(Long userId) {
        return repository.findByOwnerUserId(userId);
    }




}
