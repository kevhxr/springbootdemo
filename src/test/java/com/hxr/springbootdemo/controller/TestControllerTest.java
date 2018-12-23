package com.hxr.springbootdemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hxr.springbootdemo.StandAloneApp;
import com.hxr.springbootdemo.entity.PostReqParam;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StandAloneApp.class)
@WebAppConfiguration
public class TestControllerTest {

    @Autowired

    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp(){
        //MockMvcBuilders.webAppContextSetup(WebApplicationContext context)：
        // 指定WebApplicationContext，
        // 将会从该上下文获取相应的控制器并得到相应的MockMvc；
        //mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc = MockMvcBuilders.standaloneSetup(new TestController()).build();
    }

    @Test
    public void test1() throws Exception {
        MvcResult mvcResult=  mockMvc.perform(get("/test/get"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String content=mvcResult.getResponse().getContentAsString();
        Assert.assertEquals("doing test",content);
    }

    @Test
    public void test2() throws Exception {
        PostReqParam testPostObj = new PostReqParam();
        testPostObj.setPassWord("123");
        testPostObj.setUserName("kkk");
        String jsonStr = mapper.writeValueAsString(testPostObj);

        MvcResult mvcResult=mockMvc.perform(
                post("/test/post").contentType(MediaType.APPLICATION_JSON).content(jsonStr))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String content=mvcResult.getResponse().getContentAsString();
        Assert.assertEquals("kkk",content);
    }
}