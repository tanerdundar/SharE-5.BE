package com.tanerdundar.share5.service.concrete;

import com.tanerdundar.share5.dao.FollowRepository;
import com.tanerdundar.share5.dao.PostRepository;
import com.tanerdundar.share5.dao.UserRepository;
import com.tanerdundar.share5.entities.Follow;
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
    private final FollowRepository followRepository;


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
    public List<Post> getAllPostsByUserId(long userId) {
        return postRepository.findAllByOwnerUserId(userId);
    }

    @Override
    public List<Post> getAllActivePostsByUserId(long userId) {
        List<Post> posts = postRepository.findAllByOwnerUserId(userId);
        return getResponseEntity(posts);
    }

    @Override
    public List<Post> getAllFollowingsPostsByUserId(long userId) {
        return getAllFollowingsPosts(userId);
    }

    @Override
    public List<Post> getAllFollowingsActivePosts(long userId) {
        return getResponseEntity(getAllFollowingsPosts(userId));
    }

    @Override
    public Post createOnePost(PostCreateRequest request, long userId) {
        User postOwner = userRepository.findById(userId).orElseThrow(()-> new UserException());
        Post newPost = request.createOnePost();
        return postRepository.save(newPost);
    }

    @Override
    public void deleteOnePostByPostIdFromDB(long postId) {
        postRepository.deleteById(postId);
    }

    private List<Post> getAllFollowingsPosts(long userId) {
        User follower = userRepository.findById(userId).orElseThrow(()->new UserException());
        List<Follow> followMoves = followRepository.findAllByFollower_UserId(follower.getUserId());
        List<Post> followingsPosts= new ArrayList<>();
        for (Follow followMove : followMoves) {
            User followingUser = followMove.getFollowing();  // dikkat
            long postsOwnerId = followingUser.getUserId();
            List<Post> ownersPosts = postRepository.findAllByOwnerUserId(postsOwnerId);
            for (Post ownersPost : ownersPosts) {
                followingsPosts.add(ownersPost);
            }
        }
        return followingsPosts;
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

}
