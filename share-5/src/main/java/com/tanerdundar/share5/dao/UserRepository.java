package com.tanerdundar.share5.dao;

import com.tanerdundar.share5.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User,Long> {
}
