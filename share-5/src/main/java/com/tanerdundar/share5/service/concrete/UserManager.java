package com.tanerdundar.share5.service.concrete;

import com.tanerdundar.share5.dao.FollowRepository;
import com.tanerdundar.share5.entities.Follow;
import com.tanerdundar.share5.entities.Statu;
import com.tanerdundar.share5.exceptions.FollowException;
import com.tanerdundar.share5.exceptions.PostException;
import com.tanerdundar.share5.exceptions.UserException;
import com.tanerdundar.share5.dao.PostRepository;
import com.tanerdundar.share5.dao.UserRepository;
import com.tanerdundar.share5.entities.Post;
import com.tanerdundar.share5.entities.User;
import com.tanerdundar.share5.requests.user.UserCreateRequest;
import com.tanerdundar.share5.requests.user.UserToInactiveRequest;
import com.tanerdundar.share5.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserManager implements UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final FollowRepository followRepository;

    @Override
    public User getOneUserByUserId(long userId) {
        return userRepository.findById(userId).orElseThrow(()-> new UserException());
        }
    @Override
    public User getOneActiveUserByUserId(long userId) {
        return  getOneActiveUserById(userId);
    }
    @Override
    public User getOneUserByPostId(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new PostException());
        return getOneActiveUserById(post.getOwner().getUserId());
    }

    @Override
    public User getOneActiveUserByPostId(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new PostException());
        return getOneActiveUserById(post.getOwner().getUserId());
    }

    @Override
    public User getOneUserByFollowIdAsFollower(long followId) {
        Follow follow = followRepository.findById(followId).orElseThrow(()-> new FollowException());
        User user = follow.getFollower();
        return user;
    }

    @Override
    public User getOneActiveUserByFollowIdAsFollower(long followId) {
        Follow follow = followRepository.findById(followId).orElseThrow(()-> new FollowException());
        User user = follow.getFollower();
        return getOneActiveUserById(user.getUserId());
    }

    @Override
    public User getOneUserByFollowIdAsFollowing(long followId) {
        Follow follow = followRepository.findById(followId).orElseThrow(()-> new FollowException());
        User user = follow.getFollowing();
        return user;
    }

    @Override
    public User getOneActiveUserByFollowIdAsFollowing(long followId) {
        Follow follow = followRepository.findById(followId).orElseThrow(()-> new FollowException());
        User user = follow.getFollowing();
        return getOneActiveUserById(user.getUserId());
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllActiveUsers() {
        List<User> allUsers=userRepository.findAll();
        return getResponseEntity(allUsers);
    }

    @Override
    public List<User> getAllFollowersByUserId(long userId) {
        return  getAllFollowers(userId);
    }
    @Override
    public List<User> getAllActiveFollowersByUserId(long userId) {
        List<User> followers = getAllFollowers(userId);
        return getResponseEntity(followers);
    }
    @Override
    public List<User> getAllFollowingsByUserId(long userId) {
        return getAllFollowings(userId);
    }
    @Override
    public List<User> getAllActiveFollowingsByUserId(long userId) {
        List<User> followings = getAllFollowings(userId);
        return getResponseEntity(followings);
    }
    //-----------------------------------------------------------------------------------------

    // TODO learn Query
    
    //TODO learn Query
    //TODO Someone may use an existing inactive user name!!!
    @Override
    public User createOneUser(UserCreateRequest request) {
        List<User> users = userRepository.findAll();
        for (User value : users) {
            if (request.getUserName().equals(value.getUserName())) {
                if (value.getUserStatu() == Statu.ACTIVE) {
                    throw new UserException("Existing user name!...");
                }
            }
        }
        if(isValidEmail(request.getEMail())) {
            for (User user : users) {
                if (request.getEMail().equals(user.getEMail())) {
                    throw new UserException("Existing email adress...");
                }
            }
        } else{
            throw new UserException("Please type valid email adress...");
        }
        return  userRepository.save(request.createOneUser());
    }

    @Override
    public void deleteOneUserByUserIdFromDB(long userId) {
        userRepository.deleteById(userId);
    }


    //TODO user ve post arasındaki bağlantı kurulacak
//    @Override
//    public User toInactiveAUser(long userId, UserToInactiveRequest request) {
//        User updateUser = getOneUserByUserId(userId);
//        List<Post> userPosts =
//
//        return null;
//    }

    private List<User> getAllFollowers(long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserException());
        List<Follow> follows = followRepository.findAllByFollowing_UserId(user.getUserId());
        List<User> followers= new ArrayList<>();
        for (Follow follow : follows) {
            User foundUser = follow.getFollower();
            followers.add(foundUser);
        }
        return followers;
    }
    private List<User> getAllFollowings(long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserException());
        List<Follow> follows = followRepository.findAllByFollower_UserId(user.getUserId());
        List<User> followings= new ArrayList<>();
        for (Follow follow : follows) {
            User foundUser = follow.getFollowing();
            followings.add(foundUser);
        }
        return followings;
    }

    private List<User> getResponseEntity(List<User> users) {
        List<User> activeUsers = new ArrayList<>();
        for (User user : users) {
            if (user.getUserStatu() == Statu.ACTIVE) {
                activeUsers.add(user);
            }
        }
        return activeUsers;
    }

    private User getOneActiveUserById(long userId) {
        User returnUser = userRepository.findById(userId).orElseThrow(()-> new UserException()) ;
        if(returnUser.getUserStatu()==Statu.ACTIVE) {
            return returnUser;
        } else throw new UserException("Deleted user..");
    }
    private boolean isValidEmail(String email) {
        boolean isValid = true;
        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
        } catch (AddressException ex) {
            isValid = false;
        }
        return isValid;
    }

}




