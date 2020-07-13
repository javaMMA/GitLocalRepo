package com.xumingzhe.config;

import com.alibaba.fastjson.JSON;
import com.xumingzhe.annotations.Passport;
import com.xumingzhe.utils.CookieUtil;
import com.xumingzhe.utils.HttpclientUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2020/7/10.
 * Created by 许名哲
 * <p>
 * 作用：
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        request.getRequestDispatcher("/").forward(request,response);

        HandlerMethod hm = (HandlerMethod) handler;
        Passport methodAnnotation = hm.getMethodAnnotation(Passport.class);
        if(null != methodAnnotation ){
            return true;
        }

        //1.按照惯例获取token
        String token = "";
        String oldToken = CookieUtil.getCookieValue(request, "oldToken", true);
        if (StringUtils.isNotBlank(oldToken)) {
            token = oldToken;
        }

        String newToken = request.getParameter("token");
        if (StringUtils.isNotBlank(newToken)) {
            token = newToken;
        }

        //2.根据是否有token，要么验证token合法性、要么重定向重新登陆中心
        Map<String,String> successMap = new HashMap<>();
        String success = "fail";
        if(StringUtils.isNotBlank(token)){
            System.out.println(">>>>>>>>>>>>>>>>>"+token);
            //2.1.如果有token，获取用户IP地址
            String ip = request.getHeader("x-forwarded-for");// 通过nginx转发的客户端ip
            if(StringUtils.isBlank(ip)){
                ip = request.getRemoteAddr();// 从request中获取ip
                String ip1 = "0:0:0:0:0:0:0:1";
                if(StringUtils.isBlank(ip) || ip.equals(ip1)){
                    ip = "127.0.0.1";
                }
            }
            //2.2.去认证中心进行验证
            String successJson  = HttpclientUtil.doGet("http://localhost:8081/verify?token=" + token+"&currentIp="+ip);
            //2.3.解析验证返回的信息
            successMap = JSON.parseObject(successJson,Map.class);
            success = successMap.get("status");
            System.out.println(success);
            //2.4.判断验证通过，不通过重定向登陆中心，通过则
            if(success.equals("fail")){
                StringBuffer requestURL = request.getRequestURL();
                response.sendRedirect("http://localhost:8081/loginPage?ReturnUrl="+requestURL);
                return false;
            }

            //2.5.验证通过则，获取返回的信息
            request.setAttribute("username", successMap.get("username"));

            //2.6.覆盖cookie
            if(StringUtils.isNotBlank(token)){
                CookieUtil.setCookie(request,response,"oldToken",token,60*60*2,true);
            }
            System.out.println(successMap.get("username"));
            return true;

        }


        //3.没有token必须登录成功才能使用,重定向会passport登录
        StringBuffer requestURL = request.getRequestURL();
        response.sendRedirect("http://localhost:8081/loginPage?ReturnUrl="+requestURL);
        return false;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
