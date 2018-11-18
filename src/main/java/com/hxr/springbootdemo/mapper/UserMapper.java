package com.hxr.springbootdemo.mapper;

import com.hxr.springbootdemo.entity.UserBean;

public interface UserMapper {
    UserBean selectByUserId(Integer userId);

    Integer countUserById(Integer userId);

    Integer countAll();
}
