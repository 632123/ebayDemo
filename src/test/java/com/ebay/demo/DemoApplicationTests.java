package com.ebay.demo;

import com.alibaba.fastjson.JSON;
import com.ebay.demo.constant.HeaderConstants;
import com.ebay.demo.domain.EndPoint;
import com.ebay.demo.domain.User;
import com.ebay.demo.utils.EncodeUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

@SpringBootTest
class DemoApplicationTests {

    private static final User admin=new User("001","John","admin");
    private static final User user=new User("002","Bob","user");

    @Autowired
    private WebApplicationContext applicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }

    @Test
    void addUser() throws Exception {
        EndPoint endPoint=new EndPoint();
        endPoint.setUserId("002");
        endPoint.setEndpoint(Arrays.asList("resource A","resource B"));
        //设置了拦截器，所有从controller层开始测试
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(endPoint).getBytes())
                //这里切换user和admin用户
                        .header(HeaderConstants.USERINFO,EncodeUtils.encode(admin))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    void userAddResource() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/resource C")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HeaderConstants.USERINFO,EncodeUtils.encode(admin))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}
