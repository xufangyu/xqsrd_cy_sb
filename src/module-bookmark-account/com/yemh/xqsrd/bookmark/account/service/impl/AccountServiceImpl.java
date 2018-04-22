package com.yemh.xqsrd.bookmark.account.service.impl;

import java.util.Base64;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yemh.xqsrd.base.util.AESEncryptUtil;
import com.yemh.xqsrd.bookmark.account.service.IAccountService;

/**
 * 
 * @author xufangyu
 * @date 2017年8月19日下午5:23:04
 */
@Service("accountServiceImpl")
public class AccountServiceImpl implements IAccountService {

    private static Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Override
    public void saveAccount(Map<String, Object> map) throws Exception {

        String loginPasswdEnc = (String)map.get("loginPasswd");

        byte[] bLoginPasswd = Base64.getDecoder().decode(loginPasswdEnc);
        String loginPasswd = new String(bLoginPasswd, "UTF-8");
        log.debug("密码为：" + loginPasswd);
        // 将密码进行AES加密，salt为416592
        loginPasswd = AESEncryptUtil.encrypt(loginPasswd, "416592");
        map.put("loginPasswd", loginPasswd);

        // int a = DaoUtil.insert("x_set_account_add", map);
        // if (a < 0) {
        // System.out.println("插入失败");
        // }
        System.out.println("插入完毕" + map.toString());
        log.debug("插入完毕" + map.toString());
    }

}
