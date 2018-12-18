package com.yemh.xqsrd.freemarker;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateDemo {

    @Autowired  
    Configuration configuration; //freeMarker configuration  
    
    @Test
    public void createDemo1() throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("demoNameUp", "Bookmark");
        model.put("demoNameLower", "bookmark");
        
        Map<String, Object> tableRows = new HashMap<String, Object>();
        tableRows.put("haha1", 1);
        tableRows.put("haha2", 2);
        tableRows.put("haha3", 3);
        tableRows.put("haha4", 4);
        tableRows.put("haha5", 5);
        
        model.put("tableRows", tableRows);
        
        Template template = configuration.getTemplate("create/demoMapper.ftl");
        String processTemplateIntoString = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        
        System.out.println(processTemplateIntoString);
    }
    
    private boolean createSQL() {
        return true;
    }
    private boolean createMapper() {
        return true;
    }
    private boolean createDao() {
        return true;
    }
    private boolean createService() {
        return true;
    }
    private boolean createServiceImpl() {
        return true;
    }
    
}
