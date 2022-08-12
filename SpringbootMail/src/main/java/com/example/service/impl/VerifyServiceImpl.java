package com.example.service.impl;

import com.example.service.VerifyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class VerifyServiceImpl implements VerifyService {
    @Resource
    JavaMailSender sender;
    @Resource
    StringRedisTemplate template;
    @Value("${spring.mail.username}")
    String from;
    @Override
    public void sendVerifyCode(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("【xxx网站】注册验证码");
        Random random = new Random();
        int code = random.nextInt(89999) + 10000;
        template.opsForValue().set("verify:code"+email,code+"",3, TimeUnit.MINUTES);
        message.setText("您的注册码为:"+code+",注册码三分钟之内有效，请及时完成注册!");
        message.setTo(email);
        message.setFrom(from);
        sender.send(message);
    }

    @Override
    public boolean doVerify(String email, String code) {
        String temp = template.opsForValue().get("verify:code"+email);
        if(temp == null) return false;
        if(temp.equals(code)){
            template.delete("verify:code"+email);
            return true;
        }else {
            return false;
        }
    }
}
