package com.tanerdundar.share5.service.concrete;

import com.tanerdundar.share5.dao.FollowRepository;
import com.tanerdundar.share5.entities.Follow;
import com.tanerdundar.share5.entities.Statu;
import com.tanerdundar.share5.entities.User;
import com.tanerdundar.share5.requests.follow.FollowCreateRequest;
import com.tanerdundar.share5.service.abstracts.FollowService;
import com.tanerdundar.share5.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowManager implements FollowService {

    private final FollowRepository followRepository;
    private final UserService userService;

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
    public List<Follow> getActiveFollowerById(long userId) {
        List<Follow> followers =followRepository.findAllByFollower(userId);
        return followers;
    }
}
