package com.yemh.xqsrd.base.util;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class ResultUtil {

    public static Map<?, ?> getResult() {

        return null;
    }

    public static String S(String msg) {
        return success(msg, "");
    }

    public static String S(String msg, Object data) {
        return success(msg, data);
    }

    public static String S(String msg, List<Map<String, Object>> list, long total) {
        JSONObject res = new JSONObject();
        res.put("code", 0);
        res.put("msg", msg);
        res.put("data", list);
        res.put("count", total);
        return JSONObject.toJSONString(res);
    }

    public static String success(String msg, Object data) {
        JSONObject res = new JSONObject();
        res.put("code", 0);
        res.put("msg", msg);
        res.put("data", data);
        return JSONObject.toJSONString(res);
    }

    public static String F(String msg) {
        return fail(msg, "");
    }

    public static String F(String msg, Object data) {
        return fail(msg, data);
    }

    public static String fail(String msg, Object data) {
        JSONObject res = new JSONObject();
        res.put("code", 1);
        res.put("msg", msg);
        res.put("data", data);
        return JSONObject.toJSONString(res);
    }

}
