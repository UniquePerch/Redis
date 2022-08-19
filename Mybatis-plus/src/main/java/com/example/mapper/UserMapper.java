package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @MapKey("")
    Map<String,Object> selectMapbyId(Long id);

    Page<User> selectPageVo(@Param("page") Page<User> page,@Param("age") Integer age);
}
