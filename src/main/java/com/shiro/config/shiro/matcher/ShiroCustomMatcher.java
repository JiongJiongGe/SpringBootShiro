package com.shiro.config.shiro.matcher;

import com.shiro.config.shiro.matcher.enumconstant.ShiroLoginType;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.context.annotation.Configuration;

/**
 * @Intro: 自定义Matcher(密码匹配过程)
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/5/28
 * @Time: 下午8:57
 */
@Configuration
public class ShiroCustomMatcher extends HashedCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        ShiroCustomToken customToken = (ShiroCustomToken) token;
        //如果设置无需密码验证，则直接返回true值
        if(customToken.getType() == ShiroLoginType.NOPASSWORD){
            return true;
        }
        return false;
    }
}
