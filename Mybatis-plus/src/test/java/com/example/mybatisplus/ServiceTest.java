package com.example.mybatisplus;

import com.example.entity.User;
import com.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.rmi.CORBA.Stub;
import javax.rmi.CORBA.StubDelegate;
import java.util.LinkedList;
import java.util.List;

@SpringBootTest
public class ServiceTest {
    @Autowired
    UserService userService;
    @Test
    void GetCount(){
        long cnt = userService.count();
        System.out.println(cnt);
    }
    @Test
    void TestBatchInsert(){
        //批量添加
        List<User> list = new LinkedList<>();
        for(int i=1;i<=10;i++){
            User user = new User();
            user.setName("kk"+i);
            user.setAge(20+i);
            user.setEmail(""+i);
            list.add(user);
        }
        userService.saveBatch(list);
    }
}
