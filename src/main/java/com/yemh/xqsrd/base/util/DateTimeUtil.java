package com.yemh.xqsrd.base.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

    /**
     * 获取系统时间
     * 模板例子：yyyy-MM-dd HH:mm:ss
     */
    public static String getSystemDate(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date());
    }
}
