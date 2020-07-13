package com.xumingzhe.service;

import com.xumingzhe.vo.UmsMember;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created on 2020/7/13.
 * Created by 许名哲
 * <p>
 * 作用：
 */
@Service
public class LoginService {

    public boolean login(UmsMember umsMember){

        HashMap<String, String> tokens = new HashMap<>();

        HashMap<String,String> users = new HashMap<String,String>();
        users.put("何俊杰123456","123456");
        users.put("张文豪123456","123456");
        users.put("蔡万仕123456","123456");
        users.put("admin123","123");

        Object object = users.get(umsMember.getUsername()+umsMember.getPassword());

        if(null == object){
            return false;
        }

        return true;
    }

    public void addUserToken(String token, UmsMember umsMember) {


        umsMember.setToken(token);
    }
}
