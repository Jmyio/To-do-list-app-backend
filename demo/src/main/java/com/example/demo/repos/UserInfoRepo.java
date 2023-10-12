package com.example.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.dto.UserInfo;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {
    List<UserInfo> findByEmailAndPassword(String email, String password);
}
