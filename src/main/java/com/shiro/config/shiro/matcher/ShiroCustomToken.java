package com.shiro.config.shiro.matcher;

import com.shiro.config.shiro.matcher.enumconstant.ShiroLoginType;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @Intro: shiro自定义用户密码token
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/5/29
 * @Time: 下午5:45
 */
public class ShiroCustomToken extends UsernamePasswordToken {

    private ShiroLoginType type;   //是否需要密码登录类型


    public ShiroCustomToken(String userName, String password, ShiroLoginType type) {
        super(userName, password);
        this.type = type;
    }

    public ShiroLoginType getType() {
        return type;
    }

    public void setType(ShiroLoginType type) {
        this.type = type;
    }
}
