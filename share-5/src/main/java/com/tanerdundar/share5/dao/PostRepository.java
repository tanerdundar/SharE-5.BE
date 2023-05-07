package com.tanerdundar.share5.dao;

import com.tanerdundar.share5.entities.Post;
import com.tanerdundar.share5.entities.Statu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findAllByOwnerUserId(long userId);

}
