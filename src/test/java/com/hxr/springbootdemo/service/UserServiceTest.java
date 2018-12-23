package com.hxr.springbootdemo.service;

import com.hxr.springbootdemo.entity.UserBean;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userServiceImplmock;
    @Mock
    private LoginService loginServiceMock;
    @Mock
    private UserBean userBean;
    public static String fakePwd = "ppp";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(loginServiceMock.getPassword(userBean)).thenReturn(fakePwd);
    }

    @Test
    public void insertUserTest() {
        String response = userServiceImplmock.doLogin(userBean);
        Assert.assertEquals(response,fakePwd);
    }


}