package com.hxr.springbootdemo.mapper;

import com.hxr.springbootdemo.entity.UserBean;

import java.util.List;

public interface UserMapper {
    UserBean selectByUserId(Integer userId);

    Integer countUserByAgeLarger(Integer userAge);

    Integer countAll();

    List<UserBean> findAllUsers();

    List<UserBean> findUserByAliasAge(String userAlias,int userAge);

    int insertUser(UserBean userBean);
}
