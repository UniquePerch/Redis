package com.example.mybatisplus;

import com.example.entity.User;
import com.example.enums.SexEnum;
import com.example.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class EnumTest {
    @Resource
    UserMapper userMapper;

    @Test
    public void Test(){
        User user = new User();
        user.setName("admin");
        user.setAge(33);
        user.setSex(SexEnum.MALE);
        int res = userMapper.insert(user);
        System.out.println(res);
    }
}
