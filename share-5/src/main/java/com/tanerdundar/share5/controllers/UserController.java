package com.tanerdundar.share5.controllers;

import com.tanerdundar.share5.entities.User;
import com.tanerdundar.share5.requests.user.UserCreateRequest;
import com.tanerdundar.share5.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public User createOneUser(@RequestBody UserCreateRequest request) {
        return service.createOneUser(request);
    }
    @GetMapping("/{userId}")
    public User getOneBook(@PathVariable Long userId) {
        return service.getOneUserById(userId);
    }
}
