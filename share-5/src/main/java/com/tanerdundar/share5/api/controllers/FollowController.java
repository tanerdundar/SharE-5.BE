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

    @GetMapping("/{followId}/inactive")
    public ResponseEntity getOneFollowByFollowId(@PathVariable long followId) {
        Follow follow = followService.getOneFollowByFollowId(followId);
        return ResponseEntity.ok(follow);
    }
    @GetMapping("/{followId}")
    public ResponseEntity getOneActiveFollowByFollowId(@PathVariable long followId) {
        Follow follow = followService.getOneActiveFollowByFollowId(followId);
        return ResponseEntity.ok(follow);
    }
    @GetMapping
    public ResponseEntity getAllFollows() {
        List<Follow> follows = followService.getAllFollows();
        return ResponseEntity.ok(follows);
    }
    @GetMapping("/actives")
    public ResponseEntity getAllActiveFollows() {
        List<Follow> follows = followService.getAllActiveFollows();
        return ResponseEntity.ok(follows);
    }

    @GetMapping("/{followingId}/followers/inactives")
    public ResponseEntity getAllFollowsByFollowingId(@PathVariable long followingId) {
        List<Follow> followings = followService.getAllFollowsByFollowingId(followingId);
        return ResponseEntity.ok(followings);
    }
    @GetMapping("/{followingId}/followers")
    public ResponseEntity getAllActiveFollowsByFollowingId(@PathVariable long followingId) {
        List<Follow> followings = followService.getAllActiveFollowsByFollowingId(followingId);
        return ResponseEntity.ok(followings);
    }
    @GetMapping("/{followerId}/followings/inactives")
    public ResponseEntity getAllFollowsByFollowerId(@PathVariable long followerId) {
        List<Follow> followers = followService.getAllFollowsByFollowerId(followerId);
        return ResponseEntity.ok(followers);
    }

    @GetMapping("/{followerId}/followings")
    public ResponseEntity getAllActiveFollowsByFollowerId(@PathVariable long followerId) {
        List<Follow> follows = followService.getAllActiveFollowsByFollowerId(followerId);
        return ResponseEntity.ok(follows);
    }

//------------------------------------------------------------------------








    @PostMapping("/{followerId}")
    public ResponseEntity createOneFollowByFollowerId(@RequestBody FollowCreateRequest request, @PathVariable long followerId){
        if(request.getFollowingId()==followerId){
            return ResponseEntity.badRequest().build();
        }
        Follow follow = followService.createOneFollowByFollowerId(request,followerId);
        return ResponseEntity.ok(follow);
    }
}
