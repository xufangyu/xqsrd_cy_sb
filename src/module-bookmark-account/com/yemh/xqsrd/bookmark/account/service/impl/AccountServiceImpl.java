package com.yemh.xqsrd.bookmark.account.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yemh.xqsrd.base.AbstractBaseService;
import com.yemh.xqsrd.base.util.AESEncryptUtil;
import com.yemh.xqsrd.base.util.DateTimeUtil;
import com.yemh.xqsrd.bookmark.account.mapper.IXBookMarkMapper;
import com.yemh.xqsrd.bookmark.account.service.IAccountService;

/**
 * 
 * @author xufangyu
 * @date 2017年8月19日下午5:23:04
 */
@Service("accountServiceImpl")
public class AccountServiceImpl extends AbstractBaseService implements IAccountService {

    @Autowired
    private IXBookMarkMapper iXMapper;

    @Override
    public void saveAccount(Map<String, Object> map) throws Exception {

        String loginPasswdEnc = (String)map.get("loginPasswd");

        byte[] bLoginPasswd = Base64.getDecoder().decode(loginPasswdEnc);
        String loginPasswd = new String(bLoginPasswd, "UTF-8");
        logger.debug("密码为：" + loginPasswd);
        // 将密码进行AES加密，salt为416592
        loginPasswd = AESEncryptUtil.encrypt(loginPasswd, "416592");
        map.put("loginPasswd", loginPasswd);

        // int a = DaoUtil.insert("x_set_account_add", map);
        // if (a < 0) {
        // System.out.println("插入失败");
        // }
        System.out.println("插入完毕" + map.toString());
        logger.debug("插入完毕" + map.toString());
    }

    @Override
    public String add(Map<String, Object> params) {
        
        String dateTime = DateTimeUtil.getSystemDate("yyyy-MM-dd HH:mm:ss");
        params.put("gmtCreate", dateTime);
        params.put("gmtModified", dateTime);
        
        String loginPasswdEnc = (String)params.get("loginPasswd");

        byte[] bLoginPasswd = Base64.getDecoder().decode(loginPasswdEnc);
        String loginPasswd = null;
        try {
            loginPasswd = new String(bLoginPasswd, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        logger.debug("密码为：{}",loginPasswd);
        // 将密码进行AES加密，salt为416592
        loginPasswd = AESEncryptUtil.encrypt(loginPasswd, "416592");
        params.put("loginPasswd", loginPasswd);

        // int a = DaoUtil.insert("x_set_account_add", map);
        // if (a < 0) {
        // System.out.println("插入失败");
        // }
        int res;
        try {
            res = iXMapper.add(params);
            if (res > 0) {
                return S("添加书签成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.debug("插入完毕" + params.toString());

        return F("添加书签失败");
        
    }

}
