package com.shiro.config.shiro;

/**
 * @Intro:  shiro 登陆方式枚举类
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/5/29
 * @Time: 下午5:49
 */
public enum ShiroLoginType {

    /**
     * 1、需要密码验证登陆;2、无需密码验证登陆
     */
    PASSWORD(1),
    NOPASSWORD(2);

    private int code;

    ShiroLoginType(int code) {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
