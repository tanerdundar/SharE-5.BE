package com.tanerdundar.share5.dao;

import com.tanerdundar.share5.entities.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow,Long> {

    List<Follow> findAllByFollower_UserId(long followerId);

    List<Follow> findAllByFollowing_UserId(long followingId);
}
