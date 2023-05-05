package com.tanerdundar.share5.dao;

import com.tanerdundar.share5.entities.Statu;
import com.tanerdundar.share5.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <User,Long> {


}
