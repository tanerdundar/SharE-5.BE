package com.tanerdundar.share5.api.controllers;

import com.tanerdundar.share5.entities.Follow;
import com.tanerdundar.share5.requests.follow.FollowCreateRequest;
import com.tanerdundar.share5.service.abstracts.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/{followerId}")
    public ResponseEntity createOneFollowByFollowerId(@RequestBody FollowCreateRequest request, @PathVariable long followerId){
        if(request.getFollowingId()==followerId){
            return ResponseEntity.badRequest().build();
        }
        Follow follow = followService.createOneFollowByFollowerId(request,followerId);
      return ResponseEntity.ok(follow);
    }

    @GetMapping("/{userId}")
    public ResponseEntity getActiveFollowersByUserId(@PathVariable long userId) {
        List<Follow> follows = followService.getActiveFollowerById(userId);
        return ResponseEntity.ok(follows);
    }


}
