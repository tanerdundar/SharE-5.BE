package com.tanerdundar.share5.service.concrete;

import com.tanerdundar.share5.entities.Statu;
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
    public List<User> getFollowingsByUserId(long userId) {
         User foundUser = getOneActiveUserById(userId);
         List<User> followingsAll = foundUser.getFollowings();
         return getResponseEntity(followingsAll);
    }
    @Override
    public List<User> getFollowersByUserId(long userId) {
         User foundUser = getOneActiveUserById(userId);
         List<User> followersAll  = foundUser.getFollowers();
         return getResponseEntity(followersAll);
    }

    @Override
    public List<User> getAllActiveUsers() {
        List<User> allUsers=userRepository.findAll();
        return getResponseEntity(allUsers);
    }

    // TODO learn Query

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

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

    private List<User> getResponseEntity(List<User> followingsAll) {
        List<User> followingsActiveUsers = new ArrayList<>();
        for (User user : followingsAll) {
            if (user.getUserStatu() == Statu.ACTIVE) {
                followingsActiveUsers.add(user);
            }
        }
        return followingsActiveUsers;
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




