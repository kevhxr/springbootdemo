package com.hxr.springbootdemo.service;

import com.hxr.springbootdemo.entity.UserBean;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public String getPassword(UserBean userBean){
        return "newPassword";
    }
}
