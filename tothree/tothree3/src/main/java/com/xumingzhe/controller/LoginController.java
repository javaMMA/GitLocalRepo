package com.xumingzhe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created on 2020/7/10.
 * Created by 许名哲
 * <p>
 * 作用：
 */
@Controller
public class LoginController {

    //用户登陆
    @RequestMapping("/user/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model,
            HttpSession Session
            ){
            //登陆成功
            if(!StringUtils.isEmpty(username) && "123456".equals(password)){
                Session.setAttribute("username",username);
                return "redirect:/";
            }else {
                //登陆失败
                model.addAttribute("msg","<h1>登陆失败</h1>");
                return "test01";
            }

    }
}
