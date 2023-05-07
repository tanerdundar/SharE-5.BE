package com.tanerdundar.share5.service.abstracts;

import com.tanerdundar.share5.entities.Follow;
import com.tanerdundar.share5.requests.follow.FollowCreateRequest;

import java.util.List;

public interface FollowService  {

    Follow getOneFollowByFollowId(long followId);
    Follow getOneActiveFollowByFollowId(long followId);

    List<Follow> getAllFollows();

    List<Follow> getAllActiveFollows();
    List<Follow> getAllFollowsByFollowingId(long followingId);

    List<Follow> getAllActiveFollowsByFollowingId(long followingId);

    List<Follow> getAllFollowsByFollowerId(long followerId);

    List<Follow> getAllActiveFollowsByFollowerId(long followerId);


//------------------------------------------------------------------------


    Follow createOneFollowByFollowerId(FollowCreateRequest request, long userId);


}
