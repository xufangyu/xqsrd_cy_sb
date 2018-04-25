package com.yemh.xqsrd.menu;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import net.minidev.json.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuControllerTest extends ControllerTestBase {

    @Test
    public void testQuery() throws Exception {
        Map<String, Object> params = new HashMap<>();
        
        String content = JSONObject.toJSONString(params);
        
        mock
        .perform(MockMvcRequestBuilders.get("/getMenuList")
            .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(content)
            )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());
    }

//    @Test
//    public void testUpdate() throws Exception {
//        UserEntity user = UserMapper.getOne(3l);
//        System.out.println(user.toString());
//        user.setNickName("neo");
//        UserMapper.update(user);
//        Assert.assertTrue(("neo".equals(UserMapper.getOne(3l).getNickName())));
//    }
}