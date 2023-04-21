package com.tanerdundar.share5.service.abstracts;

import com.tanerdundar.share5.entities.User;
import com.tanerdundar.share5.requests.user.UserCreateRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getOneUserById(Long userId);

    User createOneUser(UserCreateRequest request);
}
