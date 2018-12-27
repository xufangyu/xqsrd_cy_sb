package com.yemh.xqsrd.bookmark;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONObject;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTestBase {

    @Autowired
    private WebApplicationContext context;
    protected MockMvc mock;
    private MockHttpSession mockHttpSession;

    public ControllerTestBase() {
        super();
    }

    @Before
    public void setUp() throws Exception {  
        this.mock = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();  
//        this.mockHttpSession = new MockHttpSession();  
    }
    
    public MockHttpSession getLoginSession() throws Exception {
        JSONObject json = new JSONObject();
        json.put("username", "admin");
        json.put("password_md5", "db609b344bd97fcd4a7493d09de8113e");
        json.put("remember-me", "on");
        
        if(mockHttpSession != null) {
            return mockHttpSession;
        }
        MvcResult result = this.mock
            .perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("username", "admin")
                .param("password_md5", "db609b344bd97fcd4a7493d09de8113e")
                .param("remember-me", "on")
                )
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection()).andReturn();
        mockHttpSession = (MockHttpSession)result.getRequest().getSession();
        return mockHttpSession;
    }
    
    public void testLogin() throws Exception {
        if(mockHttpSession != null) {
//            return mockHttpSession;
        }
//            .perform(MockMvcRequestBuilders.post("/login")
        MvcResult result = this.mock
                .perform(formLogin("/login").user("admin").password("password_md5","db609b344bd97fcd4a7493d09de8113e")
             )
//                .andExpect(MockMvcResultMatchers.status().isOk())  
                .andExpect(authenticated())  
            .andReturn();  
        mockHttpSession =  (MockHttpSession)result.getRequest().getSession();
        System.err.println("login status:" + result.getResponse().getStatus());
//        return mockHttpSession;
    }

}