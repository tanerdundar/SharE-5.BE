package com.tanerdundar.share5.service.concrete;

import com.tanerdundar.share5.dao.UserRepository;
import com.tanerdundar.share5.entities.Statu;
import com.tanerdundar.share5.entities.User;
import com.tanerdundar.share5.requests.user.UserCreateRequest;
import com.tanerdundar.share5.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserManager implements UserService {

    private final UserRepository repository;

    @Override
    public User getOneUserById(Long userId) {
        return repository.findById(userId).orElse(null);
    }

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
}
