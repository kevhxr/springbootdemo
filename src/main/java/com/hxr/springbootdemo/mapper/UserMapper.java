package com.hxr.springbootdemo.mapper;

import com.hxr.springbootdemo.entity.UserBean;

import java.util.List;

public interface UserMapper {
    UserBean selectByUserId(Integer userId);

    Integer countUserById(Integer userId);

    Integer countAll();

    List<UserBean> findAllUsers();
}
