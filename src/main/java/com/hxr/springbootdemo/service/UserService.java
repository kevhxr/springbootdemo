package com.hxr.springbootdemo.service;

import com.hxr.springbootdemo.entity.UserBean;

import java.util.List;

public interface UserService {

    int countUserById(int userId);

    int addUser(UserBean user);

    UserBean findUserByID(int userId);

    List<UserBean> findAllUser();
}
