package com.tanerdundar.share5.requests.follow;

import com.tanerdundar.share5.entities.Follow;
import com.tanerdundar.share5.entities.Post;
import lombok.Data;

@Data
public class FollowCreateRequest {

    private long followingId;


    public Follow createOneFollow() {
        Follow newFollow = new Follow();
        return newFollow;
    }


}
