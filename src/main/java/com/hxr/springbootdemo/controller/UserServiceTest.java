package com.hxr.springbootdemo.controller;

import com.hxr.springbootdemo.entity.UserBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/test")
public class UserServiceTest {
    @RequestMapping("getuser")
    public UserBean getUser() {
        UserBean user = new UserBean();
        user.setName("test");
        return user;
    }
}