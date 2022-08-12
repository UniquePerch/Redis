package com.example.service;

import org.springframework.stereotype.Service;

@Service
public interface VerifyService {
    void sendVerifyCode(String email);

    boolean doVerify(String mail,String code);
}
