package com.xumingzhe.controller;

import com.xumingzhe.annotations.Passport;
import com.xumingzhe.pojo.Person;
import com.xumingzhe.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2020/7/9.
 * Created by 许名哲
 * <p>
 * 作用：
 */
//引入thmeleaf模板不能用@RestControoler
@Controller
@CrossOrigin
public class HelloController {

    @Autowired
    private Person person;

    //演示yaml文件注入bean的数据
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        System.out.println(person);
        return "hello World!";
    }

    @RequestMapping("/hello2")
    @ResponseBody
    public String hello2(){
        return "hello World!";
    }

    //演示thymeleaf
    @RequestMapping("/test")
    public String thymeleafTest(Model model){
        System.out.println("11111");
        model.addAttribute("msg","<h1> hello,springboot </h1>");
        return "test";
    }


    @RequestMapping("/banner")
    @ResponseBody
    public Result banner(){
        System.out.println("按钮获取");
        return new Result("hi","普通操作",200);
    }

    @RequestMapping("/init")
    @ResponseBody
    public Result init(){
        System.out.println("首页初始化");
        return new Result("yes!","yes",200);
    }



    @RequestMapping("/ban")
    @ResponseBody
    public Result ban(HttpServletRequest request, HttpServletResponse response){
        System.out.println("按钮获取");

        return new Result("hi","可以了",111);
    }


}
