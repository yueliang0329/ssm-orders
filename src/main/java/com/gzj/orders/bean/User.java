package com.gzj.orders.bean;

import javax.validation.constraints.Pattern;

public class User {
    private Integer userId;

    @Pattern(regexp="(^[a-zA-Z0-9_-]{2,16}$)|(^[\u2E80-\u9FFF]{2,5})"
    		,message="用户名必须是2-5位中文或者2-16位英文和数字的组合")
    private String userName;

    @Pattern(regexp="(^[a-zA-Z0-9_-]{2,16}$)|(^[\u2E80-\u9FFF]{2,5})"
    		,message="密码必须是2-5位中文或者2-16位英文和数字的组合")
    private String password;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}