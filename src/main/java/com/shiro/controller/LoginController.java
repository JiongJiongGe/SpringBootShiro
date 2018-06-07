package com.shiro.controller;

import com.shiro.config.shiro.ShiroCustomToken;
import com.shiro.config.shiro.ShiroLoginType;
import com.shiro.controller.common.BaseController;
import com.shiro.response.ErrorCode;
import com.shiro.response.RpcResult;
import com.shiro.util.token.ParseTokenUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class LoginController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 通过使用用户的token，进行shiro登陆
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws IOException
     */
    @RequestMapping("/login")
    public RpcResult<Boolean> login(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        Subject subject = SecurityUtils.getSubject();
        SavedRequest sr = WebUtils.getSavedRequest(servletRequest);
        HttpServletRequest request = WebUtils.toHttp(servletRequest);
        String X_userToken = request.getHeader("X_userToken");
        Integer userID = ParseTokenUtil.parse(X_userToken);
        if(!subject.isAuthenticated()) {
            try {
                ShiroCustomToken token = new ShiroCustomToken(userID + "", "", ShiroLoginType.NOPASSWORD);
                subject.login(token);
            }catch (Exception e){
                logger.error("error = {}", e.getMessage());
                return RpcResult.ofError(ErrorCode.BIZ_ERROR.getCode(), ErrorCode.BIZ_ERROR.getMsg("shiro验证失败"));
            }
        }
        WebUtils.issueRedirect(servletRequest, servletResponse, sr.getRequestUrl());
        return RpcResult.ofSuccess(true);
    }

}
