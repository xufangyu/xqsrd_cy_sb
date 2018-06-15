package com.yemh.xqsrd.account.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageRowBounds;
import com.yemh.xqsrd.account.mapper.IXRoleMapper;
import com.yemh.xqsrd.account.mapper.IXUserMapper;
import com.yemh.xqsrd.account.service.AccountService;
import com.yemh.xqsrd.account.service.RoleService;
import com.yemh.xqsrd.base.util.DateTimeUtil;
import com.yemh.xqsrd.base.util.MD5Util;
import com.yemh.xqsrd.base.util.StringUtil;

@Service("RoleServiceImpl")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private IXRoleMapper ixRoleMapper;

    /* (non-Javadoc)
     * @see com.yemh.xqsrd.account.service.impl.RoleService#add(java.util.Map)
     */
    @Override
    public String add(Map<String, Object> params) {

        String dateTime = DateTimeUtil.getSystemDate("yyyy-MM-dd HH:mm:ss");
        params.put("gmtCreate", dateTime);
        params.put("gmtModified", dateTime);
        
        String roleName = StringUtil.getStringValue(params,"roleName");

        String roleDescription = StringUtil.getStringValue(params,"roleDescription");
        params.put("roleName", roleName);
        params.put("roleDescription", roleDescription);
        
        int res;
        try {
            res = ixRoleMapper.add(params);
            if (res > 0) {
                JSONObject data = new JSONObject();
                data.put("data", "");
                data.put("msg", "添加账号成功");
                data.put("code", 0);
                return JSONObject.toJSONString(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject data = new JSONObject();
            data.put("data", "");
            data.put("msg", "添加账号失败");
            data.put("code", 1);

            return JSONObject.toJSONString(data);
        }
        JSONObject data = new JSONObject();
        data.put("data", "");
        data.put("msg", "添加账号成功");
        data.put("code", 0);

        return JSONObject.toJSONString(data);
    }

    /* (non-Javadoc)
     * @see com.yemh.xqsrd.account.service.impl.RoleService#upd(java.util.Map)
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
            res = ixRoleMapper.upd(params);
            if (res < 0) {

            }
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject data = new JSONObject();
            data.put("data", "");
            data.put("msg", "更新账号失败");
            data.put("code", 1);

            return JSONObject.toJSONString(data);
        }
        JSONObject data = new JSONObject();
        data.put("data", "");
        data.put("msg", "更新账号成功");
        data.put("code", 0);

        return JSONObject.toJSONString(data);
    }

    /* (non-Javadoc)
     * @see com.yemh.xqsrd.account.service.impl.RoleService#del(java.util.Map)
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
            res = ixRoleMapper.del(params);
            if (res <= 0) {
                JSONObject data = new JSONObject();
                data.put("data", "");
                data.put("msg", "删除账号失败");
                data.put("code", 1);

                return JSONObject.toJSONString(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject data = new JSONObject();
            data.put("data", "");
            data.put("msg", "删除账号失败");
            data.put("code", 1);

            return JSONObject.toJSONString(data);
        }
        JSONObject data = new JSONObject();
        data.put("data", "");
        data.put("msg", "删除账号成功");
        data.put("code", 0);

        return JSONObject.toJSONString(data);
    }

    /* (non-Javadoc)
     * @see com.yemh.xqsrd.account.service.impl.RoleService#getList(java.util.Map)
     */
    @Override
    public String getList(Map<String, Object> params) {

        int pageNum = 0, pageSize = 0;
        String page = (String)params.get("page");
        if (StringUtil.isEmpty(page)) {
            pageNum = 1;
        } else {
            pageNum = Integer.parseInt(page);
        }
        String limit = (String)params.get("limit");
        if (StringUtil.isEmpty(limit)) {
            pageSize = 20;
        } else {
            pageSize = Integer.parseInt(limit);
        }

        Page<Map<String, Object>> pageList = null;
        try {
            pageList = ixRoleMapper.getRoleList(new PageRowBounds(pageNum, pageSize), params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject data = new JSONObject();
        data.put("data", pageList);
        data.put("count", pageList.getTotal());
        data.put("msg", "获取所有菜单成功");
        data.put("code", 0);

        return JSONObject.toJSONString(data);
    }

    /* (non-Javadoc)
     * @see com.yemh.xqsrd.account.service.impl.RoleService#getUserById(java.util.Map)
     */
    @Override
    public String getRoleById(Map<String, Object> params) {
        // TODO Auto-generated method stub
        return null;
    }

}
