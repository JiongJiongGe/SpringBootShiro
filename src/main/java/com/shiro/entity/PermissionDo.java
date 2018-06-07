package com.shiro.entity;

import java.io.Serializable;


public class PermissionDo implements Serializable {

    private Integer id;           //主键

    private String name;          //名称

    private String url;    //请求路径Url

    private String permissionMessage;    //权限字符串

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermissionMessage() {
        return permissionMessage;
    }

    public void setPermissionMessage(String permissionMessage) {
        this.permissionMessage = permissionMessage;
    }
}