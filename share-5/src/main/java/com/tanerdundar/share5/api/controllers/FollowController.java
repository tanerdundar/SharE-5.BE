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

    @GetMapping("/{followerId}/followings")
    public ResponseEntity getActiveFollowsByFollowerId(@PathVariable long followerId) {
        List<Follow> follows = followService.getActiveFollowsByFollowerId(followerId);
        return ResponseEntity.ok(follows);
    }
    @GetMapping("/{followingId}/followers")
    public ResponseEntity getActiveFollowsByFollowingId(@PathVariable long followingId) {
        List<Follow> followings = followService.getActiveFollowsByFollowingId(followingId);
        return ResponseEntity.ok(followings);
    }


}
