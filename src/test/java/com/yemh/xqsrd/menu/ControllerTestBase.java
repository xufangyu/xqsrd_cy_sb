package com.yemh.xqsrd.menu;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
        this.mock = MockMvcBuilders.webAppContextSetup(context).build();  
        this.mockHttpSession = new MockHttpSession();  
    }
    
    @SuppressWarnings("unused")
    private HttpSession getLoginSession() throws Exception {
        if(mockHttpSession != null) {
            return mockHttpSession;
        }
        MvcResult result = this.mock
            .perform(MockMvcRequestBuilders.get("/user/userMsg/loginUser/loginUser")
             )  
            .andExpect(MockMvcResultMatchers.status().isOk())  
            .andReturn();  
        mockHttpSession =  (MockHttpSession)result.getRequest().getSession();
        return mockHttpSession;
    }

}