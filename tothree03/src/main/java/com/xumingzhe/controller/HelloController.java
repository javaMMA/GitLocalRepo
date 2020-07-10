package com.xumingzhe.controller;

import com.xumingzhe.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2020/7/9.
 * Created by 许名哲
 * <p>
 * 作用：
 */
//引入thmeleaf模板不能用@RestControoler
@Controller
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
        System.out.println(person);
        return "hello World!";
    }

    //演示thymeleaf
    @RequestMapping("/test")
    public String thymeleafTest(Model model){
        System.out.println("11111");
        model.addAttribute("msg","<h1> hello,springboot </h1>");
        return "test01";
    }
}
