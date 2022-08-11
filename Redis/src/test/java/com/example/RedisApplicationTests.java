package com.example;

import com.example.mapper.MainMapper;
import com.example.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class RedisApplicationTests {
    @Resource
    RedisTemplate<Object,Object> template;
    @Resource
    RedisService service;

    @Test
    void contextLoads() {
//        template.opsForValue().set("a","999");
//        System.out.println(template.opsForValue().get("a"));
//        template.delete("a");
//        System.out.println(template.hasKey("a"));
        service.test();
        System.out.println(template.opsForValue().get("d"));
    }

    @Autowired
    MainMapper mapper;
    @Test
    void test(){
        mapper.getSid();
        mapper.getSid();
        mapper.getSid();
    }
}
