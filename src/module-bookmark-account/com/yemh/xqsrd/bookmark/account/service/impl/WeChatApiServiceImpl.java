package com.yemh.xqsrd.bookmark.account.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageRowBounds;
import com.yemh.xqsrd.base.AbstractBaseService;
import com.yemh.xqsrd.base.util.AESEncryptUtil;
import com.yemh.xqsrd.base.util.DateTimeUtil;
import com.yemh.xqsrd.base.util.StringUtil;
import com.yemh.xqsrd.bookmark.account.mapper.IXBookmarkMapper;
import com.yemh.xqsrd.bookmark.account.service.IBookmarkService;
import com.yemh.xqsrd.bookmark.account.service.IWeChatApiService;

/**
 * @author yemh
 * @date 2018/12/24
 */
@Service("WeChatApiService")
public class WeChatApiServiceImpl extends AbstractBaseService  implements IWeChatApiService {

    @Autowired
    private IXBookmarkMapper iXMapper;
    
    @Value("${bookmark.key}")
    String bookmarkKey;
    
    private final String splator = ":";

    @Override
    public String getListByUrlKey(Map<String, Object> params) {

        int pageNum = 0, pageSize = 10;
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
            pageList = iXMapper.getListByUrlKey(new PageRowBounds(pageNum, pageSize), params);
            if(pageList != null) {
                StringBuilder sb = new StringBuilder();
                // 将密码解密返回
                for (int i = 0; i < pageList.size(); i++) {
                    Map<String, Object> map = pageList.get(i);
                    String websiteUrl = String.valueOf(map.get("websiteUrl"));
                    String loginName = String.valueOf(map.get("loginName"));
                    String passwordEnc = String.valueOf(map.get("loginPasswd"));
                    String password = AESEncryptUtil.decrypt(passwordEnc, bookmarkKey);
//                    map.put("loginPasswd", password);
                    
                    sb.append(websiteUrl).append(splator)
                    .append(loginName).append(splator)
                    .append(password).append(splator)
                    .append("\r\n");
                }
                
//                return S("获取所有成功", pageList, pageList.getTotal());
                return sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return F("获取失败");
    }


    @Override
    public String setDeleteByIndex(Map<String, Object> params) {

        int pageNum = 0, pageSize = 10;
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
        
        int deleteIndex = 0;
        String deleteIndexStr = (String)params.get("deleteIndex"); 
        if (StringUtil.isEmpty(deleteIndexStr)) {
            return F("删除失败");
        } else {
            deleteIndex = Integer.parseInt(deleteIndexStr);
        }

        Page<Map<String, Object>> pageList = null;
        try {
            pageList = iXMapper.getListByUrlKey(new PageRowBounds(pageNum, pageSize), params);
            if(pageList != null) {
                String deleteXId = "";
                // 将密码解密返回
                for (int i = 0; i < pageList.size(); i++) {
                    Map<String, Object> map = pageList.get(i);
                    String xId = String.valueOf(map.get("xId"));
                    if( i + 1 == deleteIndex) {
                        deleteXId = xId;
                        break;
                    }
                }
                
                int flag = iXMapper.del(deleteXId);
                
                if(flag >0) {
                    return S("删除成功");
                }
                
//                return S("获取所有成功", pageList, pageList.getTotal());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return F("删除失败");
    }
}
