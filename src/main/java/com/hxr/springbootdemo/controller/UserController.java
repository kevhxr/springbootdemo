package com.hxr.springbootdemo.controller;

import com.hxr.springbootdemo.entity.UserBean;
import com.hxr.springbootdemo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/get/{userId}")
    public UserBean findUserById(@PathVariable("userId") int userId){
        return userService.findUserByID(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/count/{userId}")
    public int countUserById(@PathVariable("userId") int userId){
        return userService.countUserById(userId);
    }
}
