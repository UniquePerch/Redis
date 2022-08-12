package com.example.controller;

import com.example.entity.RestBean;
import com.example.service.VerifyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/auth")
public class ApiController {
    @Resource
    VerifyService verifyService;

    @RequestMapping(value = "/verify-code",method = RequestMethod.GET)
    public RestBean verifyCode(@RequestParam("email") String email){
        try{
            verifyService.sendVerifyCode(email);
            return new RestBean(200,"邮件发送成功");
        }catch (Exception e){
            return new RestBean(500,"邮件发送失败");
        }
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public RestBean register(String username, String password, String email, String verify){
        if(verifyService.doVerify(email,verify)){
            return new RestBean(200,"注册成功");
        }
        else {
            return new RestBean(403,"验证码错误");
        }
    }
}
