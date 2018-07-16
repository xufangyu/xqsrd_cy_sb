package com.yemh.xqsrd.account.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.aspectj.util.GenericSignature.ArrayTypeSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageRowBounds;
import com.yemh.xqsrd.account.mapper.IXPermissionMenuMapper;
import com.yemh.xqsrd.account.service.PermissionMenuService;
import com.yemh.xqsrd.base.AbstractBaseService;
import com.yemh.xqsrd.base.util.DateTimeUtil;
import com.yemh.xqsrd.base.util.MD5Util;
import com.yemh.xqsrd.base.util.StringUtil;

@Service("PermissionMenuServiceImpl")
public class PermissionMenuServiceImpl extends AbstractBaseService implements PermissionMenuService {

    @Autowired
    private IXPermissionMenuMapper iXMapper;

    @Transactional
    @Override
    public String add(Map<String, Object> params) {

        String dateTime = DateTimeUtil.getSystemDate("yyyy-MM-dd HH:mm:ss");
        params.put("gmtCreate", dateTime);
        params.put("gmtModified", dateTime);
        
        String permissionName = StringUtil.getStringValue(params,"permissionName");

        String permissionDescription = StringUtil.getStringValue(params,"permissionDescription");
        params.put("permissionName", permissionName);
        params.put("permissionDescription", permissionDescription);
        
        JSONObject obj = (JSONObject)JSONObject.toJSON(params);
        String permMenuIds = obj.getString("permMenuIds");
        if(StringUtil.isEmpty(permMenuIds)) {
            return F("添加失败");
        }

        List<String> menuIds = Arrays.asList(permMenuIds.split(","));
        params.put("menuIds", menuIds);
        
        int res1;
        int res2;
        try {
            res1 = iXMapper.add(params);
            if (res1 > 0) {
                
                res2 = iXMapper.addPermRelMenu(params);
                
                return S("添加账号成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return F("添加账号失败");
    }

    /* (non-Javadoc)
     * @see com.yemh.xqsrd.account.service.impl.permissionService#upd(java.util.Map)
     */
    @Override
    public String upd(Map<String, Object> params) {
        String dateTime = DateTimeUtil.getSystemDate("yyyy-MM-dd HH:mm:ss");
        // params.put("gmtCreate", dateTime);
        params.put("gmtModified", dateTime);

        String loginName = StringUtil.getStringValue(params,"loginName");

        String password = StringUtil.getStringValue(params,"password");
        // 使用"{登录名}"作为盐
        password = MD5Util.getMD5(password + "{" + loginName + "}");
        params.put("password", password);
        
        int res;
        try {
            res = iXMapper.upd(params);
            if (res > 0) {
                return S("更新账号成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return F("更新账号失败");
    }

    /* (non-Javadoc)
     * @see com.yemh.xqsrd.account.service.impl.permissionService#del(java.util.Map)
     */
    @Override
    public String del(Map<String, Object> params) {
        // JSONObject json = (JSONObject)JSONObject.toJSON(params);
        // JSONArray xIds = json.getJSONArray("xIds");
        ArrayList<String> xIds = (ArrayList<String>)params.get("xIds");
        // 判断是否为批量删除，如果为单个删除，将id放入list中
        if (StringUtil.isEmpty(xIds)) {
            xIds = new ArrayList<>();
            xIds.add(String.valueOf(params.get("xId")));
        }
        // 管理员账号不允许删除
        if(xIds.contains(new Integer(1))) {
            xIds.remove(new Integer(1));
        }
        
        params.put("xIds", xIds);

        int res;
        try {
            res = iXMapper.del(params);
            if (res > 0) {
                return S("删除账号成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return F("删除账号失败");
    }

    @Override
    public String getList(Map<String, Object> params) {

        int pageNum = 0, pageSize = 20;
        pageNum = (int)params.get("page");
//        if (StringUtil.isEmpty(page)) {
//            pageNum = 1;
//        } else {
//            pageNum = Integer.parseInt(page);
//        }
        pageSize = (int)params.get("limit");
//        if (StringUtil.isEmpty(limit)) {
//            pageSize = 20;
//        } else {
//            pageSize = Integer.parseInt(limit);
//        }

        Page<Map<String, Object>> pageList = null;
        try {
            pageList = iXMapper.getList(new PageRowBounds(pageNum, pageSize), params);
            if(pageList != null) {
                return S("获取所有菜单权限成功", pageList, pageList.getTotal());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return F("获取所有菜单权限失败");
    }

    @Override
    public String getMenuPermissionById(Map<String, Object> params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getListAllForRole(Map<String, Object> params) {
        int pageNum = 0, pageSize = 20;
        pageNum = (int)params.get("page");
        pageSize = (int)params.get("limit");

        Page<Map<String, Object>> pageList = null;
        try {
            pageList = iXMapper.getListAllForRole(new PageRowBounds(pageNum, pageSize), params);
            if (pageList != null) {
                return S("获取所有菜单权限成功", pageList, pageList.getTotal());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return S("获取所有菜单权限成功");
    }

    @Override
    public String getListByRoleId(Map<String, Object> params) {
        int pageNum = 0, pageSize = 20;
        // pageNum = (int)params.get("page");
        // pageSize = (int)params.get("limit");

        Page<Map<String, Object>> pageList = null;
        try {
            pageList = iXMapper.getListByRoleId(params);
            if (pageList != null) {
                // 不返回count前台接收不到这个字段就不会分页
                // data.put("count", pageList.getTotal());
                return S("根据角色,获取所有菜单权限成功", pageList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return F("根据角色,获取所有菜单权限失败");
    }

}
