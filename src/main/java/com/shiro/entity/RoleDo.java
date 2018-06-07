package com.shiro.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

public class RoleDo implements Serializable{

    private Integer id;

    private String role;    // 角色身份

    private String intro;  // 角色介绍

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}