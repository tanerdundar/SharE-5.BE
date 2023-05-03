package com.tanerdundar.share5.service.concrete;

import com.tanerdundar.share5.entities.Statu;
import com.tanerdundar.share5.exceptions.PostException;
import com.tanerdundar.share5.exceptions.UserException;
import com.tanerdundar.share5.dao.PostRepository;
import com.tanerdundar.share5.dao.UserRepository;
import com.tanerdundar.share5.entities.Post;
import com.tanerdundar.share5.entities.User;
import com.tanerdundar.share5.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserManager implements UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;


//    @Override
//    public User createOneUser(UserCreateRequest request) {
//            List<User> allUsers = userRepository.findAll();
//            User foundUser;
//            for()
//
//
//
//            for (int i=0;i< allUsers.size();i++) {
//                if(request.getUserName()==allUsers.get(i).getUserName()){
//                    throw new UserException("Existing user!.. Please type a new name.");
//                } else if(request.getEMail()==allUsers.get(i).getEMail()){
//                    throw new UserException("Existing email!.. Please type a new email.");
//                }else if(request.getUserName().length()<5) {
//                    throw new UserException("At least 5 character please...");
//                } else {
//                    User userToCreate = new User();
//                    userToCreate.setUserName(request.getUserName());
//                    userToCreate.setPassword(request.getPassword());
//                    userToCreate.setEMail(request.getEMail());
//                    return userRepository.save(userToCreate);
//                }
//            }
//        }

    @Override
    public User getOneUserByUserId(long userId) {
        return userRepository.findById(userId).orElseThrow(()-> new UserException());

        }

    @Override
    public User getOneUserByPostId(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new PostException());
        return userRepository.findById(post.getOwner().getUserId()).orElseThrow(()-> new UserException());

    }

    // TODO findById yerine getOneUser kullan
    @Override
    public List<User> getFollowingsByUserId(long userId) {
        User foundUser = userRepository.findById(userId).orElseThrow(()-> new UserException());
        if(foundUser.getUserStatu()==Statu.ACTIVE) {
            List<User> followingsAll = foundUser.getFollowings();
            return getResponseEntity(followingsAll);
        } throw new UserException();

    }

    private List<User> getResponseEntity(List<User> followingsAll) {
        List<User> followingsActiveUsers = new ArrayList<>();
        for (User user : followingsAll) {
            if (user.getUserStatu() == Statu.ACTIVE) {
                followingsActiveUsers.add(user);
            }
        }
        return followingsActiveUsers;
    }

    @Override
    public List<User> getFollowersByUserId(long userId) {
        User foundUser = userRepository.findById(userId).orElseThrow(()-> new UserException());
        if(foundUser.getUserStatu()==Statu.ACTIVE){
            List<User> followersAll  = foundUser.getFollowers();
            return getResponseEntity(followersAll);
        } else {
            throw new UserException();
        }

    }

    @Override
    public List<User> getAllActiveUsers() {
        List<User> allUsers=userRepository.findAll();
        return getResponseEntity(allUsers);
    }

    // TODO Query i öğren

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();

    }
}


//    @Override
//    public User createOneUser(UserCreateRequest request) {
//        User userToSave = new User();
//
//        userToSave.setUserName(request.getUserName());
//        userToSave.setPassword(request.getPassword());
//        userToSave.setEMail(request.getEMail());
//        userToSave.setProfileName(request.getProfileName());
//        userToSave.setPosts(request.getPosts());
//        userToSave.setFollowers(request.getFollowers());
//        userToSave.setFollowings(request.getFollowings());
//
//        return repository.save(userToSave);
//    }

//    @Override
//    public User deleteOneUserById(Long userId, UserDeleteRequest request) {
//        Optional<User> user =repository.findById(userId);
//        if(user.isPresent()) {
//        User userToDelete =user.get();
//        userToDelete.setUserStatu(Statu.INACTIVE);
//
//        return repository.save(userToDelete);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public List<User> getAllActiveUsers() {
//        return repository.findByUserStatu(Statu.ACTIVE);
//    }
//    @Override
//    public List<User> getAllUsers() {
//        return repository.findAll();
//    }
//
//    @Override
//    public User getOneUserById(Long userId) {
//
//        Optional<User> activeCheck = repository.findById(userId);
//        if(activeCheck.isPresent()) {
//            User user = activeCheck.get();
//            if(user.getUserStatu()==Statu.ACTIVE) {
//                return user;
//            }
//        }
//       return null;
//    }

    //------------------------------------------------------------------------------------------------------------------------------
//    @Override
//    public List<Post> getFollowingsPosts(long userId) {
//        User user = repository.findById(userId).orElse(null);
//              // .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
//        List<Post> followingsPosts = new ArrayList<>();
//        for (User following : user.getFollowings()) {
//            followingsPosts.addAll(following.getPosts());
//        }
//        return followingsPosts;
//    }
    //------------------------------------------------------------------------------------------------------------------------------




