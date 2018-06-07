package com.shiro.config.shiro;

import com.google.gson.Gson;
import com.shiro.config.shiro.bean.SavedRequestBean;
import com.shiro.entity.PermissionDo;
import com.shiro.sevice.PermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jnlp.PersistenceService;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/6/1
 * @Time: 下午4:55
 */
public class MyFilter extends AccessControlFilter {

    private static Logger logger = LoggerFactory.getLogger(MyFilter.class);

    public static final String DEFAULT_LOGIN_URL = "/login.jsp";
    public static final String GET_METHOD = "GET";
    public static final String POST_METHOD = "POST";
    private String loginUrl = "/login";

    private static final String noPermissUrl = "/shiro/no/permission";

    @Autowired
    private PermissionService permissionService;

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        HttpServletRequest httpRequest = WebUtils.toHttp(servletRequest);
        String actionUrl = WebUtils.getRequestUri(httpRequest);
        Subject subject = SecurityUtils.getSubject();
        PermissionDo permission = permissionService.getByUrl(actionUrl);
        logger.info("yunkai = {}, actionUrl = {}", getPathWithinApplication(servletRequest), actionUrl);
        if(loginUrl.equals(getPathWithinApplication(servletRequest)) || (noPermissUrl.equals(getPathWithinApplication(servletRequest))) || subject.isPermitted(permission.getPermissionMessage())){
            return true;
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        HttpServletRequest httpRequest = WebUtils.toHttp(servletRequest);
        if(!subject.isAuthenticated() && !pathsMatch(loginUrl, servletRequest)){
            Session session = subject.getSession();
            Map<String, Object> reqParams = new HashMap<String, Object>();
            Enumeration em = httpRequest.getParameterNames();
            //获取post请求body中请求的参数
            while (em.hasMoreElements()) {
                String name = (String) em.nextElement();
                String value = httpRequest.getParameter(name);
                reqParams.put(name, value);
            }
            SavedRequestBean savedRequestBean = new SavedRequestBean(httpRequest, reqParams);
            session.setAttribute("savedRequestBean", savedRequestBean);
            saveRequestAndRedirectToLogin(servletRequest, servletResponse);
        } else{
            String actionUrl = WebUtils.getRequestUri(httpRequest);
            PermissionDo permission = permissionService.getByUrl(actionUrl);
            logger.info("url = {}", subject.isPermitted(permission.getPermissionMessage()));
            if(!subject.isPermitted(permission.getPermissionMessage())){
                WebUtils.issueRedirect(servletRequest, servletResponse, noPermissUrl);
            } else {
                return false;
            }
        }
       return false;
    }
}
