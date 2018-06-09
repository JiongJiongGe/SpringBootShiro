package com.shiro.interceptor;

import com.google.gson.Gson;
import com.shiro.entity.UserDo;
import com.shiro.sevice.PermissionService;
import com.shiro.sevice.UserService;
import com.shiro.util.token.ParseTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/6/5
 * @Time: 上午9:47
 */
public class WebInInterceptor implements HandlerInterceptor{

    private static Logger logger = LoggerFactory.getLogger(WebInInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String X_userToken = httpServletRequest.getHeader("X_userToken");
        Integer userID = ParseTokenUtil.parse(X_userToken);
        httpServletRequest.setAttribute("userID", userID);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
