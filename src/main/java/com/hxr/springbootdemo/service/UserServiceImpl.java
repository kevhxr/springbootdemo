package com.hxr.springbootdemo.service;

import com.hxr.springbootdemo.entity.UserBean;
import com.hxr.springbootdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int countUserByAgeLarger(int userAge) {
        return userMapper.countUserByAgeLarger(userAge);
    }

    @Override
    public int addUser(UserBean user) {
        return 0;
    }

    @Override
    public UserBean findUserByID(int userId) {
        return userMapper.selectByUserId(userId);
    }

    @Override
    public List<UserBean> findAllUser() {
        return userMapper.findAllUsers();
    }

    @Override
    public List<UserBean> findUserByAliasAge(String userAlias, int userAge) {
        return userMapper.findUserByAliasAge(userAlias,userAge);
    }

    @Override
    public int insertUser(UserBean userBean) {
        return userMapper.insertUser(userBean);
    }
}
