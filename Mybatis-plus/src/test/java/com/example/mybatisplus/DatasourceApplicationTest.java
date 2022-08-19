package com.example.mybatisplus;

import com.example.entity.Product;
import com.example.entity.User;
import com.example.service.ProductService;
import com.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class DatasourceApplicationTest {
    @Resource
    UserService userService;

    @Resource
    ProductService productService;

    @Test
    void contextLoads() {
        User user = userService.getById(1L);
        Product product = productService.getById(1L);
        System.out.println("User = " + user);
        System.out.println("Product = " + product);
    }
}
