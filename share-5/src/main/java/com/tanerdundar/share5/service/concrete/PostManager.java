package com.tanerdundar.share5.service.concrete;

import com.tanerdundar.share5.dao.PostRepository;
import com.tanerdundar.share5.dao.UserRepository;
import com.tanerdundar.share5.entities.Post;
import com.tanerdundar.share5.entities.Statu;
import com.tanerdundar.share5.entities.User;
import com.tanerdundar.share5.exceptions.PostException;
import com.tanerdundar.share5.exceptions.UserException;
import com.tanerdundar.share5.requests.post.PostCreateRequest;
import com.tanerdundar.share5.service.abstracts.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostManager implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;


    @Override
    public Post getOnePostByPostId(long postId) {
        return postRepository.findById(postId).orElseThrow(()-> new PostException()) ;
    }

    @Override
    public Post getOneActivePostByPostId(long postId) {
        return getOneActivePostById((postId));
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getAllActivePosts() {
        List<Post> posts = postRepository.findAll();
        return getResponseEntity(posts);
    }
    @Override
    public List<Post> findAllByOwnerUserId(long userId) {
        return postRepository.findAllByOwnerUserId(userId);
    }
    @Override
    public List<Post> getAllActivePostsByUserId(long userId) {
        User postOwner = userRepository.findById(userId).orElseThrow(()-> new UserException());
        if(postOwner.getUserStatu()==Statu.ACTIVE){
            List<Post> usersPosts = postOwner.getPosts();
            return getResponseEntity(usersPosts);
        } else throw new UserException();

    }

    @Override
    public List<Post> getAllFollowingsPosts(long userId) {
        List<Post> followingsPosts = getFollowingsPosts(userId);
        return followingsPosts;
    }

    @Override
    public List<Post> getAllFollowingsActivePosts(long userId) {
            List<Post> followingsPosts = getFollowingsPosts(userId);
        return getResponseEntity(followingsPosts);
    }

    @Override
    public Post createOnePost(PostCreateRequest request, long userId) {
        User postOwner = userRepository.findById(userId).orElseThrow(()-> new UserException());
        Post newPost = request.createOnePost();
        newPost.setOwner(postOwner);
        List<Post> userPosts= postOwner.getPosts();
        userPosts.add(newPost);
        postOwner.setPosts(userPosts);
        return postRepository.save(newPost);
    }



    private Post getOneActivePostById(long postId) {
       Post returnPost = postRepository.findById(postId).orElseThrow(()-> new PostException()) ;
        if(returnPost.getPostStatu()== Statu.ACTIVE) {
            return returnPost;
        } else throw new PostException("Deleted post..");
    }

    private List<Post> getResponseEntity(List<Post> allPosts) {
        List<Post> activePosts = new ArrayList<>();
        for (Post post : allPosts) {
            if (post.getPostStatu() == Statu.ACTIVE) {
                activePosts.add(post);
            }
        }
        return activePosts;
    }
    private List<Post> getFollowingsPosts(long userId) {
        User flowOwner = userRepository.findById(userId).orElseThrow(()-> new UserException());
        List<User> followingUsers= flowOwner.getFollowings();
        List<Post> followingsPosts= new ArrayList<>();
        for (User followingUser : followingUsers) {
            for (int j = 0; j < followingUser.getPosts().size(); j++) {
                followingsPosts.add(followingUser.getPosts().get(j));
            }
        }
        return followingsPosts;
    }
}
