package com.example.mybatisplus;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;

@SpringBootTest
class MybatisPlusApplicationTests {
    @Resource
    UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }
    @Test
    void insert(){
        User user = new User();
        user.setAge(60);
        user.setName("张三");
        user.setEmail("@qq.com");
        int res = userMapper.insert(user);
        System.out.println(res);
    }

    @Test
    void delete(){
        //根据map集合中的条件删除
//        Map<String,Object> mp = new HashMap<>();
//        mp.put("name","张三");
//        mp.put("age",60);
//        int res = userMapper.deleteByMap(mp);
        List<Long> list = Arrays.asList(1L, 2L, 3L);
        userMapper.deleteBatchIds(list);
    }

    @Test
    void update(){
        User user = new User();
        user.setId(4L);
        user.setName("李四");
        user.setEmail("lisi@qq.com");
        int res = userMapper.updateById(user);
        System.out.println(res);
    }

    @Test
    void select(){
        //通过id查询
//        User = userMapper.selectById(4L);
//        System.out.println(user);
//        List<Long> list = Arrays.asList(4L, 5L);
//        List<User> users = userMapper.selectBatchIds(list);
//        users.forEach(System.out::println);
        //通过map查询
//        Map<String,Object> map = new HashMap<>();
//        map.put("name","李四");
//        List<User> list = userMapper.selectByMap(map);
//        list.forEach(System.out::println);
        //自定义查询
//        Map<String,Object> map = userMapper.selectMapbyId(4L);
//        System.out.println(map);
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }
}
