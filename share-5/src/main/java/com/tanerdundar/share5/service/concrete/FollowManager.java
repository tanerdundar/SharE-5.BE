package com.tanerdundar.share5.service.concrete;

import com.tanerdundar.share5.dao.FollowRepository;
import com.tanerdundar.share5.entities.Follow;
import com.tanerdundar.share5.entities.Post;
import com.tanerdundar.share5.entities.Statu;
import com.tanerdundar.share5.entities.User;
import com.tanerdundar.share5.exceptions.FollowException;
import com.tanerdundar.share5.exceptions.PostException;
import com.tanerdundar.share5.requests.follow.FollowCreateRequest;
import com.tanerdundar.share5.service.abstracts.FollowService;
import com.tanerdundar.share5.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowManager implements FollowService {

    private final FollowRepository followRepository;
    private final UserService userService;



    @Override
    public Follow getOneFollowByFollowId(long followId) {
        return followRepository.findById(followId).orElseThrow(()-> new FollowException());
    }

    @Override
    public Follow getOneActiveFollowByFollowId(long followId) {
        return getOneActiveFollowById(followId);
    }

    @Override
    public List<Follow> getAllFollows() {
        return followRepository.findAll();
    }

    @Override
    public List<Follow> getAllActiveFollows() {
        return getResponseEntity(followRepository.findAll());
    }
    @Override
    public List<Follow> getAllFollowsByFollowingId(long followingId) {
        List<Follow> follows =followRepository.findAllByFollowing_UserId(followingId);
        return follows;
    }
    @Override
    public List<Follow> getAllActiveFollowsByFollowingId(long followingId) {
        List<Follow> follows =followRepository.findAllByFollowing_UserId(followingId);
        return getResponseEntity(follows);
    }

    @Override
    public List<Follow> getAllFollowsByFollowerId(long followerId) {
        List<Follow> follows = followRepository.findAllByFollower_UserId(followerId);
        return follows;
    }
    @Override
    public List<Follow> getAllActiveFollowsByFollowerId(long followerId) {
        List<Follow> follows =followRepository.findAllByFollower_UserId(followerId);
        return getResponseEntity(follows);
    }
    @Override
    public Follow createOneFollowByFollowerId(FollowCreateRequest request, long userId) {
        Follow follow = request.createOneFollow();
        User follower = userService.getOneActiveUserByUserId(userId);
        User following = userService.getOneActiveUserByUserId(request.getFollowingId());
        follow.setFollower(follower);
        follow.setFollowing(following);
        return followRepository.save(follow);
    }

    @Override
    public void deleteOneFollowByFollowIdFormDB(long followId) {
        followRepository.deleteById(followId);
    }

    private Follow getOneActiveFollowById(long followId) {
        Follow returnFollow = followRepository.findById(followId).orElseThrow(()-> new FollowException());
        if(returnFollow.getFollowStatu()== Statu.ACTIVE) {
            return returnFollow;
        } else throw new FollowException("Deleted follow..");
    }
    private List<Follow> getResponseEntity(List<Follow> allFollows) {
        List<Follow> activeFollows = new ArrayList<>();
        for (Follow follow : allFollows) {
            if (follow.getFollowStatu() == Statu.ACTIVE) {
                activeFollows.add(follow);
            }
        }
        return activeFollows;
    }
}
