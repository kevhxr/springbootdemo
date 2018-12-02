package com.hxr.springbootdemo.controller;

import com.hxr.springbootdemo.entity.UserBean;
import com.hxr.springbootdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource(name = "userServiceImpl")
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/get/{userId}")
    public UserBean findUserById(@PathVariable("userId") int userId) {
        return userService.findUserByID(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/count/{userId}")
    public int countUserById(@PathVariable("userId") int userId) {
        return userService.countUserById(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/get/all")
    public List<UserBean> findAllUsers() {
        logger.info("findallusers==========");
        List<UserBean> users = userService.findAllUser();
        return users;
    }
}
