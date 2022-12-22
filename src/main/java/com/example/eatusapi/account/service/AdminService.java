package com.example.eatusapi.account.service;

import com.example.eatusapi.account.entity.User;
import com.example.eatusapi.account.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    public UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(AdminService.class);
    @Autowired
    public  AdminService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    // 모든 회원 정보 반환
    public List<User> getAllUsers()
    {
        logger.info("전체 회원 정보 반환 !");
        List<User> userList = userRepository.findAll();
        return userList;
    }

    // 해당 회원 탐색
    public User getUserById(String uid)
    {
        logger.info("특정 회원 정보 반환 !");
        User user = new User();
        try {
            user = userRepository.getByUserId(uid);
        }catch (Exception e)
        {
            logger.info("회원 정보 없음");
            return user;
        }

        return user;
    }

    //회원 삭제
    public void deleteUserById(long uid)
    {
        logger.info("회원 삭제 시작 id : {}",uid);
        try{
            userRepository.deleteUserById(uid);
        }catch (Exception e)
        {
            logger.info(e.getMessage());
            logger.info("회원 삭제 실패");
        }
    }
}
