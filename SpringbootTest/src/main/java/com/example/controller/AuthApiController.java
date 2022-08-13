package com.example.controller;

import com.example.entity.resp.RestBean;
import com.example.service.AccountService;
import com.example.service.VerifyService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

@Api(tags = "用户验证",description = "用户登录成功失败的返回实体等")
@RestController
@RequestMapping("/api/auth")
public class AuthApiController {
    @Resource
    VerifyService verifyService;
    @Resource
    AccountService accountService;

    @ApiResponses({
            @ApiResponse(code = 200, message = "邮件发送成功"),
            @ApiResponse(code = 500, message = "邮件发送失败")   //不同返回状态码描述
    })
    @ApiOperation("请求邮箱验证码")
    @RequestMapping(value = "/verify-code",method = RequestMethod.GET)
    public RestBean<Void> verifyCode(@ApiParam("邮箱地址") @RequestParam("email") String email){
        try{
            verifyService.sendVerifyCode(email);
            return new RestBean<>(200,"邮件发送成功");
        }catch (Exception e){
            return new RestBean<>(500,"邮件发送失败");
        }
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 403, message = "失败")   //不同返回状态码描述
    })
    @ApiOperation("用户注册")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public RestBean<Void> register(@ApiParam("用户名") @RequestParam String username,
                                   @ApiParam("密码") @RequestParam String password,
                                   @ApiParam("邮箱") @RequestParam String email,
                                   @ApiParam("验证码") @RequestParam String verify){
        if(verifyService.doVerify(email,verify)){
            accountService.createAccount(username,password);
            return new RestBean<>(200,"注册成功");
        }
        else {
            return new RestBean<>(403,"验证码错误");
        }
    }

    @PostMapping(value = "/login-success")
    public RestBean<Void> loginSuccess(){
        return new RestBean<>(200,"登陆成功");
    }

    @GetMapping(value = "/logout-success")
    public RestBean<Void> logoutSuccess(){
        return new RestBean<>(200,"退出成功");
    }

    @PostMapping("/login-failure")
    public RestBean<Void> loginFailure(){
        return new RestBean<>(304,"登录失败,用户名或密码错误");
    }

    @ApiIgnore
    @RequestMapping("/access-deny")
    public RestBean<Void> accessDeny(){
        return new RestBean<>(401,"未验证，请先进行登录!");
    }
}
