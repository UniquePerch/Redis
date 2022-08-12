package com.example.controller;

import com.example.entity.Student;
import com.example.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@Controller
public class MainController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @Resource
    TestService testService;

    @RequestMapping("/login")
    public String login(HttpServletRequest request){
//        Logger logger = LoggerFactory.getLogger(MainController.class);
//        logger.info("访问login");
        testService.test();
        System.out.println("我是同步方法");
        return "login";
    }

    @RequestMapping("/student")
    @ResponseBody
    public List<Student> student(){
        List<Student> students = new LinkedList<>();
        Student student = new Student();
        student.setName("小明");
        student.setSex("男");
        student.setSid(10);
        students.add(student);
        students.add(student);
        return students;
    }
}

