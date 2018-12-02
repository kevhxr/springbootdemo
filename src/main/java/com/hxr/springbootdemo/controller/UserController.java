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
    @RequestMapping(value = "/count/{userAge}")
    public int countUserByAgeLarger(@PathVariable("userAge") int userAge) {
        return userService.countUserByAgeLarger(userAge);
    }

    @ResponseBody
    @RequestMapping(value = "/get/all")
    public List<UserBean> findAllUsers() {
        logger.info("findallusers==========");
        List<UserBean> users = userService.findAllUser();
        return users;
    }

    @ResponseBody
    @RequestMapping(value = "/get/{userAlias}/{userAge}")
    public List<UserBean> findUserByAliasAge(
            @PathVariable("userAlias") String userAlias,
            @PathVariable("userAge") int userAge) {
        logger.info("findUserByAliasAge==========");
        List<UserBean> users = userService.findUserByAliasAge(userAlias,userAge);
        return users;
    }

    @ResponseBody
    @RequestMapping(value = "/add/{userName}/{userAlias}/{userAge}")
    public int insertUser(
            @PathVariable("userName") String userName,
            @PathVariable("userAlias") String userAlias,
            @PathVariable("userAge") int userAge) {
        logger.info("going to insert User==========");

        UserBean userBean = new UserBean();
        userBean.setUserName(userName);
        userBean.setUserAge(userAge);
        userBean.setUserAlias(userAlias);
        int insertRowNum = userService.insertUser(userBean);
        logger.info("successfully insert User=========="+userBean.toString());
        return insertRowNum;
    }
}
