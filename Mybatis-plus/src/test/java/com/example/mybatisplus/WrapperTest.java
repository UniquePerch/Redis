package com.example.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class WrapperTest {
    @Resource
    UserMapper userMapper;

    @Test
    public void test1(){
        //查询用户名包含a，年龄20-30之间，邮箱不为空的用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","a").between("age",20,30).isNotNull("email");
        List<User> userList =userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void test2(){
//        查询信息，按照年龄排序，若年龄相同，则按照id升序排序
//        SELECT id,name,age,email,is_deleted FROM t_user WHERE is_deleted=0 ORDER BY age DESC,id ASC
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age")
                .orderByAsc("id");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test3(){
//        删除邮箱为空的用户
//        UPDATE t_user SET is_deleted=1 WHERE is_deleted=0 AND (email IS NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int res = userMapper.delete(queryWrapper);
        System.out.println(res);
    }
    @Test
    public void test4(){
        //修改用户名包含a，年龄大于20，或者邮箱为空的用户
//        UPDATE t_user SET name=?, email=? WHERE is_deleted=0 AND (age > ? AND name LIKE ? OR email IS NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age",20)
                .like("name","a")
                .or()
                .isNull("email");
        User user = new User();
        user.setName("小明");
        user.setEmail("666");
        int res = userMapper.update(user,queryWrapper);
        System.out.println(res);
    }

    @Test
    public void test5(){
        //修改用户名包含a并且（年龄大于20或者邮箱为空）的用户
        //lambada表达式中条件优先执行
//        UPDATE t_user SET name=?, email=? WHERE is_deleted=0 AND (name LIKE ? AND (age > ? OR email IS NULL))
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name","a")
                .and(userQueryWrapper -> userQueryWrapper.gt("age",20).or().isNull("email"));
        User user = new User();
        user.setName("小红");
        user.setEmail("666");
        int res = userMapper.update(user,wrapper);
        System.out.println(res);
    }
    @Test
    public void test6(){
//        查询用户的用户名年龄和邮箱
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name","age","email");
        List<Map<String,Object>> list = userMapper.selectMaps(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test7(){
        //查询id小于等于100的用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("id","select id from t_user where id <= 5");
        List<User>list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test8(){
        //修改用户名包含a并且（年龄大于20或者邮箱为空）的用户
//        UPDATE t_user SET name=?,email=? WHERE is_deleted=0 AND (name LIKE ? AND (age > ? OR email IS NULL))
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.like("name","a")
                .and(i->i.gt("age",20).or().isNull("email"));
        wrapper.set("name","小黑").set("email","777");
        int res = userMapper.update(null,wrapper);
        System.out.println(res);
    }

    @Test
    public void test9(){
        //SELECT id,name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (age >= ? AND age <= ?)
        String username = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            wrapper.like("name",username);
        }
        if(ageBegin!=null){
            wrapper.ge("age",ageBegin);
        }
        if(ageEnd!=null){
            wrapper.le("age",ageEnd);
        }
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test10(){
//        SELECT id,name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (name LIKE ? AND age <= ?)
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(username),"name",username)
                .ge(ageBegin != null ,"age" ,ageBegin)
                .le(ageEnd != null,"age",ageEnd);
        List<User> list = userMapper.selectList(wrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test11(){
        //SELECT id,name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (name LIKE ? AND age <= ?)
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(username),User::getName,username)
                .ge(ageBegin != null,User::getAge,ageBegin)
                .le(ageEnd!=null,User::getAge,ageEnd);
        List<User> list = userMapper.selectList(wrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test12(){
        //修改用户名包含a并且（年龄大于20或者邮箱为空）的用户
//        UPDATE t_user SET name=?,email=? WHERE is_deleted=0 AND (name LIKE ? AND (age > ? OR email IS NULL))
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.like(User::getName,"a")
                .and(i->i.gt(User::getAge,20).or().isNull(User::getEmail));
        wrapper.set(User::getName,"小黑").set(User::getEmail,"777");
        int res = userMapper.update(null,wrapper);
        System.out.println(res);
    }
}
