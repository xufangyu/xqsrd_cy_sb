package com.yemh.xqsrd.account;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yemh.xqsrd.account.mapper.IXUserMapper;
import com.yemh.xqsrd.account.pojo.XUser;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private IXUserMapper ixUserMapper;

//    @Test
//    public void testInsert() throws Exception {
//        UserMapper.insert(new UserEntity("aa", "a123456", UserSexEnum.MAN));
//        UserMapper.insert(new UserEntity("bb", "b123456", UserSexEnum.WOMAN));
//        UserMapper.insert(new UserEntity("cc", "b123456", UserSexEnum.WOMAN));
//
//        Assert.assertEquals(3, UserMapper.getAll().size());
//    }

    @Test
    public void testQuery() throws Exception {
        XUser users = ixUserMapper.getByLoginName("yemh");
        System.out.println(users.toString());
        Assert.assertNotNull(users);
    }

//    @Test
//    public void testUpdate() throws Exception {
//        UserEntity user = UserMapper.getOne(3l);
//        System.out.println(user.toString());
//        user.setNickName("neo");
//        UserMapper.update(user);
//        Assert.assertTrue(("neo".equals(UserMapper.getOne(3l).getNickName())));
//    }
}