package com.yemh.xqsrd.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
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
    String basePath = "./src/test/java/";
    
    Map<String, Object> model = new HashMap<String, Object>();
    Map<String, Object> tableRows = new HashMap<String, Object>();

    @Autowired  
    Configuration configuration; //freeMarker configuration  
    
    @Before
    public void init() {
        String demoNameLower = "bookmark";
        String demoNameUp = getMapCamelCaseToUnderscore(demoNameLower);
        
        model.put("demoNameLower", demoNameLower);
        model.put("demoNameUp", demoNameUp);

        String row1 = "website_name";
        String row1Name = getMapCamelCaseToUnderscore(row1);
        tableRows.put(row1, row1Name);
        String row2 = "website_url";
        String row2Name = getMapCamelCaseToUnderscore(row2);
        tableRows.put(row2, row2Name);
        String row3 = "login_name";
        String row3Name = getMapCamelCaseToUnderscore(row3);
        tableRows.put(row3, row3Name);
        String row4 = "login_passwd";
        String row4Name = getMapCamelCaseToUnderscore(row4);
        tableRows.put(row4, row4Name);
        String row5 = "bind_mail";
        String row5Name = getMapCamelCaseToUnderscore(row5);
        tableRows.put(row5, row5Name);
        String row6 = "disc";
        String row6Name = row6;
        tableRows.put(row6, row6Name);
        
        model.put("tableRows", tableRows);
    }
    
    @Test
    public void createAll() throws Exception {
        createSQL();
        createMapper();
        createDao();
        createService();
        createServiceImpl();
    }
    
    @Test
    public void createDemo1() throws Exception {
        
        Template template = configuration.getTemplate("create/demoMapper.ftl");
        String processTemplateIntoString = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        
        System.out.println(processTemplateIntoString);
    }
    @Test
    public void createSQL() throws Exception {
        FileWriter fw = new FileWriter(basePath + "demoSQL.sql");
        Template template = configuration.getTemplate("create/demoSQL.ftl");
        String processTemplateIntoString = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        
        fw.write(processTemplateIntoString);
        fw.flush();
        fw.close();
        System.out.println(processTemplateIntoString);
    }
    @Test
    public void createMapper() throws Exception {
        FileWriter fw = new FileWriter(basePath + "demoMapper.xml");
        Template template = configuration.getTemplate("create/demoMapper.ftl");
        String processTemplateIntoString = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        
        fw.write(processTemplateIntoString);
        fw.flush();
        fw.close();
        System.out.println(processTemplateIntoString);
    }
    @Test
    public void createDao() throws Exception {
        FileWriter fw = new FileWriter(basePath + "demoIMapper.java");
        Template template = configuration.getTemplate("create/demoIMapper.ftl");
        String processTemplateIntoString = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        
        fw.write(processTemplateIntoString);
        fw.flush();
        fw.close();
        System.out.println(processTemplateIntoString);
    }
    @Test
    public void createService() throws Exception {
        FileWriter fw = new FileWriter(basePath + "demoService.java");
        Template template = configuration.getTemplate("create/demoService.ftl");
        String processTemplateIntoString = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        
        fw.write(processTemplateIntoString);
        fw.flush();
        fw.close();
        System.out.println(processTemplateIntoString);
    }
    @Test
    public void createServiceImpl() throws Exception {
        FileWriter fw = new FileWriter(basePath + "demoServiceImpl.java");
        Template template = configuration.getTemplate("create/demoServiceImpl.ftl");
        String processTemplateIntoString = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        
        fw.write(processTemplateIntoString);
        fw.flush();
        fw.close();
        System.out.println(processTemplateIntoString);
    }
    
    /**
     * 根据下划线获取驼峰结构
     * @return
     * @throws Exception 
     */
    private String getMapCamelCaseToUnderscore(String row) {
        String[] names = row.split("_");
//        if(names.length < 1) {
//            throw new Exception("请输入带下划线的列字段");
//        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < names.length; i++) {
            // 如果没有下划线则开头字母大写
            if(i==0 && names.length > 1) {
                sb.append(names[0]);
                continue;
            }
            
            for (int i1 = 0; i1 < names[i].length(); i1++) {
                if(i1 == 1) {
                    // 将字符串转换成字符数组
                    char[] ch = names[i].toCharArray();
                    if (ch[0] >= 'a' && ch[0] <= 'z') {
                        ch[0] = (char) (ch[0] - 32);
                    }
                    String strT = new String(ch);
                    sb.append(strT);
                }
            }
        }
        
        return sb.toString();
    }
}
