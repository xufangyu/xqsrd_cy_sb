package com.yemh.xqsrd.menu.mapper;

import java.util.List;
import java.util.Map;

/**
 * @author yemh
 * @date 2018/04/26
 */
public interface IXMenuMapper {

    List<Map<String,Object>> getMenuList();

    List<Map<String, Object>> getAllMenuList();

    List<Map<String, Object>> getParentMenuList();

    int addMenu(Map<String, Object> params);

    int updMenu(Map<String, Object> params);

}
