package com.example.eatusapi.account.repository;

import com.example.eatusapi.account.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User getByUserId(String uid);

    @Transactional
    void deleteUserById(long id);
}
