package com.tanerdundar.share5.service.concrete;

import com.tanerdundar.share5.dao.PostRepository;
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

            return repository.save(postToSave);

        }

    }

    @Override
    public Post getOnePostById(Long postId) {
        return repository.findById(postId).orElse(null);
    }

    @Override
    public List<Post> getPostsByUserId(long userId) {
        User postsOwner = manager.getOneUserById(userId);
        if (postsOwner == null) {
            return null;
        }
        else{
         return
    }
}
