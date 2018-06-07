package com.shiro.entity;

import java.io.Serializable;

public class UserDo implements Serializable {

    private Integer id;

    private String userName;      //用户名

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}