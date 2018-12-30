package com.yemh.xqsrd.base.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SHA1Util {

    /**
     * 对字符串SHA1加密(小写+字母)
     *
     * @param str 传入要加密的字符串
     * @return SHA1加密后的字符串
     */
    public static String getSHA1(String str) {
        try {
            // 生成一个SHA1加密计算摘要
            MessageDigest md = MessageDigest.getInstance("SHA1");
            // 计算SHA1函数
            md.update(str.getBytes());
            // digest()最后确定返回SHA1 hash值，返回值为8位字符串。因为SHA1 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 对字符串SHA1加密(大写+数字)
     *
     * @param str 传入要加密的字符串
     * @return SHA1加密后的字符串
     */

    public static String SHA1(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        try {
            byte[] btInput = s.getBytes();
            // 获得SHA1摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("SHA1");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String getWCSignature(Map<String, Object> map, String token) {
        String nonce = String.valueOf(map.get("nonce"));
        String timestamp = String.valueOf(map.get("timestamp"));
        
        List<String> params = new ArrayList<String> ();
        params.add(nonce);
        params.add(timestamp);
        params.add(token);
        
        Collections.sort(params);
        StringBuilder sb = new StringBuilder();
        for (String str : params) {
            sb.append(str);
        }
        String sha1 = getSHA1(sb.toString());
        
        return sha1;
    }
    
    
    public static void main(String[] args) {
        String nonce = "471197615";
        String timestamp = "1546176542";
        String token = "wx20171229";
        String sha1 = SHA1(timestamp + nonce + token);
        String sha1_2 = getSHA1(timestamp + nonce + token);
        System.out.println(sha1);
        System.out.println(sha1_2);
    }
    
}