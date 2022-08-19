package com.example.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.example.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user")
public class User {
//    @TableId(value = "uid")
    private Long id;
//    @TableField("user_name") 属性对应的字段名
    private String name;
    private Integer age;
    private String email;
    @TableLogic
    private Integer isDeleted;

    private SexEnum sex;
}
