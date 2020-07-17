package com.xumingzhe.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created on 2020/7/13.
 * Created by 许名哲
 * <p>
 * 作用：
 */
@Data
public class UmsMember implements Serializable {

    private String id;
    private String memberLevelId;
    private String username;
    private String password;
    private String nickname;
    private String phone;
    private int status;
    private LocalDateTime createTime;
    private String icon;
    private int gender;
    private LocalDateTime birthday;
    private String city;
    private String job;
    private String personalizedSignature;
    private int sourceType;
    private int integration;
    private int growth;
    private int luckeyCount;
    private int historyIntegration;
    private String token;
}
