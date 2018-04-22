package com.yemh.xqsrd.base.util;

import java.util.List;
import java.util.Map;

public class StringUtil {

    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }
    
    public static boolean isEmpty(List<?> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }
    
    public static boolean isEmpty(Map<?,?> map) {
        if (map == null || map.size() == 0) {
            return true;
        }
        return false;
    }
    
    /**
     * 字符串忽略大小写比对
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;
        }
        return str1.equalsIgnoreCase(str2);
    }
}
