package com.yemh.xqsrd.bookmark;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import net.minidev.json.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookmarkControllerTest extends ControllerTestBase {

    @Test
//    @WithUserDetails(value = "admin", userDetailsServiceBeanName = "customUserService")
    public void testQuery() throws Exception {
        Map<String, Object> params = new HashMap<>();
        
        String content = JSONObject.toJSONString(params);
        
        mock
        .perform(MockMvcRequestBuilders.post("/bookmark/list")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(content)
            .session(getLoginSession())
            )
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk());
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