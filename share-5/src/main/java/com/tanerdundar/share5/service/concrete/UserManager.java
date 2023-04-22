package com.tanerdundar.share5.service.concrete;

import com.tanerdundar.share5.dao.UserRepository;
import com.tanerdundar.share5.entities.Statu;
import com.tanerdundar.share5.entities.User;
import com.tanerdundar.share5.requests.user.UserCreateRequest;
import com.tanerdundar.share5.requests.user.UserDeleteRequest;
import com.tanerdundar.share5.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserManager implements UserService {

    private final UserRepository repository;



    @Override
    public User createOneUser(UserCreateRequest request) {
        User userToSave = new User();

        userToSave.setUserName(request.getUserName());
        userToSave.setPassword(request.getPassword());
        userToSave.setEMail(request.getEMail());
        userToSave.setProfileName(request.getProfileName());
        userToSave.setPosts(request.getPosts());
        userToSave.setFollowers(request.getFollowers());
        userToSave.setFollowings(request.getFollowings());

        return repository.save(userToSave);
    }

    @Override
    public User deleteOneUserById(Long userId, UserDeleteRequest request) {
        Optional<User> user =repository.findById(userId);
        if(user.isPresent()) {
        User userToDelete =user.get();
        userToDelete.setUserStatu(Statu.INACTIVE);

        return repository.save(userToDelete);
        } else {
            return null;
        }
    }

    @Override
    public List<User> getAllActiveUsers() {
        return repository.findByUserStatu(Statu.ACTIVE);
    }
    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getOneUserById(Long userId) {

        Optional<User> activeCheck = repository.findById(userId);
        if(activeCheck.isPresent()) {
            User user = activeCheck.get();
            if(user.getUserStatu()==Statu.ACTIVE) {
                return user;
            }
        }
       return null;
    }

}
