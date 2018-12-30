package com.yemh.xqsrd.xml;

import java.io.*;
import java.util.*;
import org.dom4j.*;
import org.dom4j.io.*;

public class MyXMLReader {
    public static void main(String arge[]) {
        SAXReader reader = new SAXReader();
        File file = new File("books.xml");
        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        
        List<Element> childElements = root.elements();
        for (Element child : childElements) {
            //未知属性名情况下
            /*List<Attribute> attributeList = child.attributes();
            for (Attribute attr : attributeList) {
                System.out.println(attr.getName() + ": " + attr.getValue());
            }*/
              
            //已知属性名情况下
            System.out.println("id: " + child.attributeValue("id"));
              
            //未知子元素名情况下
            /*List<Element> elementList = child.elements();
            for (Element ele : elementList) {
                System.out.println(ele.getName() + ": " + ele.getText());
            }
            System.out.println();*/
              
            //已知子元素名的情况下
            System.out.println("title" + child.elementText("title"));
            System.out.println("author" + child.elementText("author"));
            //这行是为了格式化美观而存在
            System.out.println();
        }
    }
}