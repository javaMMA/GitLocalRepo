package com.xumingzhe.controller;

import com.alibaba.fastjson.JSON;
import com.xumingzhe.service.LoginService;
import com.xumingzhe.utils.JwtUtil;
import com.xumingzhe.vo.UmsMember;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2020/7/13.
 * Created by 许名哲
 * <p>
 * 作用：
 */
@Controller
public class PassportController {

    @RequestMapping("/loginPage")
    public String loginPage(String ReturnUrl, ModelMap map){
        map.put("ReturnUrl",ReturnUrl);
        return "loginPage";
    }

    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    @ResponseBody
    public String login(UmsMember umsMember, HttpServletRequest request){

        String token = "";
        //1.查询数据库
        boolean login = loginService.login(umsMember);
        if (false == login){
            return "fail";
        }

        //2.登录成功，用jwt制作token
        String username = umsMember.getUsername();
        String password = umsMember.getPassword();
        Map<String,Object> userMap = new HashMap<>();
        userMap.put("username",username);
//        userMap.put("password",password);

        //获取用户IP
        String ip = request.getHeader("x-forwarded-for");// 通过nginx转发的客户端ip
        if(StringUtils.isBlank(ip)){
            ip = request.getRemoteAddr();// 从request中获取ip
            String ip1 = "0:0:0:0:0:0:0:1";
            if(StringUtils.isBlank(ip) || ip.equals(ip1)){
                ip = "127.0.0.1";
            }
        }
        // 按照设计的算法对参数进行加密后，生成token
        token = JwtUtil.encode("xumingzhe", userMap, ip);

        // 将token存入redis一份
        loginService.addUserToken(token,umsMember);

        return token;
    }

    @RequestMapping("/verify")
    @ResponseBody
    public String verify(String token,String currentIp){

        //1.判断是否能够成功解码
        Map<String, Object> decode = JwtUtil.decode(token, "xumingzhe", currentIp);

        Map<String,String> map = new HashMap<>();

        if(decode!=null){
            map.put("status","success");
            map.put("username",(String)decode.get("username"));
//            map.put("nickname",(String)decode.get("nickname"));
        }else{
            map.put("status","fail");
        }
        return JSON.toJSONString(map);
    }
}
