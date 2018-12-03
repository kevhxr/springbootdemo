package com.hxr.springbootdemo.service;

import com.hxr.springbootdemo.entity.UserBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    int countUserByAgeLarger(int userAge);

    int addUser(UserBean user);

    UserBean findUserByID(int userId);

    List<UserBean> findAllUser();

    List<UserBean> findUserByAliasAge(String userAlias,int userAge);

    @Transactional
    int insertUser(UserBean userBean) throws Exception;
}
