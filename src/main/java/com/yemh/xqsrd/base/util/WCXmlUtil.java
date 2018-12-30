package com.yemh.xqsrd.base.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.alibaba.fastjson.JSONObject;

public class WCXmlUtil {
    
    /**
     * 解包数据
<xml><ToUserName><![CDATA[gh_f986ffd5840b]]></ToUserName>
<FromUserName><![CDATA[oHQu90i-m3lKrC8K4vNYG9ithKVE]]></FromUserName>
<CreateTime>1546181050</CreateTime>
<MsgType><![CDATA[text]]></MsgType>
<Content><![CDATA[hhh]]></Content>
<MsgId>6640797043896535681</MsgId>
</xml>
     * @param info
     * @return
     */
    public static Map<String, Object> parseInfo(String info){
        byte[] bytes = info.getBytes();
        InputStream is = new ByteArrayInputStream(bytes);
        
        SAXReader reader = new SAXReader();
        Document doc = null;
        try {
            doc = reader.read(is);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = doc.getRootElement();

        String toUserName = getValue(root, "ToUserName");
        String fromUserName = getValue(root,"FromUserName");
        String createTime = getValue(root, "CreateTime");
        
        String msgType = getValue(root, "MsgType");
        String content = getValue(root, "Content");
        String msgId = getValue(root, "MsgId");

        Map<String, Object> infoMap = new HashMap<>();
        infoMap.put("toUserName", toUserName);
        infoMap.put("fromUserName", fromUserName);
        infoMap.put("createTime", createTime);
        
        infoMap.put("msgType", msgType);
        infoMap.put("content", content);
        infoMap.put("msgId", msgId);
        
//        System.out.println(new JSONObject(infoMap).toJSONString());
        
        return infoMap;
    }
    
    public static String getValue(Element root, String key) {
        Element element = root.element(key);
        String value = element.getText();
        return value;
    }
    
    /**
     * 打包数据
<xml>
  <ToUserName>gh_f986ffd5840b</ToUserName>
  <FromUserName>oHQu90i-m3lKrC8K4vNYG9ithKVE</FromUserName>
  <CreateTime>1546181050</CreateTime>
  <MsgType>text</MsgType>
  <Content>hhh</Content>
</xml>
     * @param paramsMap
     * @return
     */
    public static String packInfo(Map<String, Object> paramsMap) {
        Document doc = DocumentHelper.createDocument();
        //增加根节点
        Element root = doc.addElement("xml");
        
        //增加子元素
        Element toUserName = root.addElement("ToUserName");
        Element fromUserName = root.addElement("FromUserName");
        Element createTime = root.addElement("CreateTime");
        
//        toUserName.setData(String.valueOf(paramsMap.get("toUserName")));
        toUserName.setText(packValue(paramsMap ,"toUserName"));
        fromUserName.setText(packValue(paramsMap ,"fromUserName"));
        createTime.setText(String.valueOf(paramsMap.get("createTime")));
        
        Element msgType = root.addElement("MsgType");
        Element content = root.addElement("Content");
//        Element msgId = root.addElement("MsgId");

        msgType.setText(packValue(paramsMap ,"msgType"));
        content.setText(packValue(paramsMap ,"content"));
//        msgId.setText(String.valueOf(paramsMap.get("msgId")));
        
        //实例化输出格式对象
        OutputFormat format = OutputFormat.createPrettyPrint();
        //设置输出编码
        format.setEncoding("UTF-8");
        // 删除xml头
        format.setSuppressDeclaration(true);
//        format.setTrimText(true);
//        format.setIndent(true);
//        format.setNewlines(false);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        //生成XMLWriter对象，构造函数中的参数为需要输出的文件流和格式
        XMLWriter writer = null;
        try {
            writer = new XMLWriter(baos, format);
            writer.setEscapeText(false);
            // 开始写入，write方法中包含上面创建的Document对象
            writer.write(doc);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        String str = baos.toString();
//        System.out.println(str);
        return str;
    }
    
    public static String packValue(Map<String, Object> paramsMap, String key) {
        String prefix = "<![CDATA[";
        String subfix = "]]>";
        String str = String.valueOf(paramsMap.get(key));
        
        return prefix + str + subfix;
    }
}