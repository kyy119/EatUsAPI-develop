package com.example.eatusapi.account.controller;

import com.example.eatusapi.account.entity.User;
import com.example.eatusapi.account.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    Logger logger = LoggerFactory.getLogger(AdminController.class);
    public AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService)
    {
        this.adminService = adminService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers()
    {
        List<User> users = adminService.getAllUsers();
        for(User user : users)
        {
            logger.info("user : {} " ,user.toString());
        }

        return users;
    }
    @GetMapping("/find/{uid}")
    public User getUser(@RequestParam String uid) throws Exception
    {
        User user = adminService.getUserById(uid);
        logger.info("find user : {} " ,user.toString());
        return user;
    }

    @GetMapping("/delete/{user_id}")
    public void deleteUser(@RequestParam(value = "user_id") String uid) throws Exception
    {
        long num= Long.parseLong(uid);
        adminService.deleteUserById(num);
    }

}
