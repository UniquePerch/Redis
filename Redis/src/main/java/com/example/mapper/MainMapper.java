package com.example.mapper;

import com.example.cache.MybatisRedisCache;
import com.example.entity.Account;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@CacheNamespace(implementation = MybatisRedisCache.class)
@Mapper
public interface MainMapper {
    @Select("select name from student where sid = 1")
    String getSid();
}
