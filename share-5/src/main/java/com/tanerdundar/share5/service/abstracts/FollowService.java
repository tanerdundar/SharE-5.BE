package com.tanerdundar.share5.service.abstracts;

import com.tanerdundar.share5.entities.Follow;
import com.tanerdundar.share5.requests.follow.FollowCreateRequest;

import java.util.List;

public interface FollowService  {
    Follow createOneFollowByFollowerId(FollowCreateRequest request, long userId);

    List<Follow> getActiveFollowerById(long userId);
}
