package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

@Data
@TableName("product")
public class Product {
    private Long id;
    private String name;
    private Integer price;
    @Version
    private Integer version;
}