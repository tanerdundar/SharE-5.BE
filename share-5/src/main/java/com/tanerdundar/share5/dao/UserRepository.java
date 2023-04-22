package com.tanerdundar.share5.dao;

import com.tanerdundar.share5.entities.Statu;
import com.tanerdundar.share5.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository <User,Long> {
    List<User> findByUserStatu(Statu userStatu);


}
