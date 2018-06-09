package com.shiro.config.shiro.filter;

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

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Intro: 自定义判断权限filter
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/6/1
 * @Time: 下午4:55
 */
public class ShiroCustomAccessFilter extends AccessControlFilter {

    private static Logger logger = LoggerFactory.getLogger(ShiroCustomAccessFilter.class);

    private String loginUrl = "/login";

    private static final String noPermissUrl = "/no/permission";

    @Autowired
    private PermissionService permissionService;

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        logger.info("action url = {}", getPathWithinApplication(servletRequest));
        if(loginUrl.equals(getPathWithinApplication(servletRequest)) || (noPermissUrl.equals(getPathWithinApplication(servletRequest))) || checkPermission(servletRequest, subject)){
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
            if(!checkPermission(servletRequest, subject)){
                WebUtils.issueRedirect(servletRequest, servletResponse, noPermissUrl);
            } else {
                return false;
            }
        }
       return false;
    }

    /**
     * 验证用户权限
     *
     * @param servletRequest
     * @param subject
     * @return
     */
    private boolean checkPermission(ServletRequest servletRequest, Subject subject){
        HttpServletRequest request = WebUtils.toHttp(servletRequest);
        String actionUrl = WebUtils.getRequestUri(request);
        PermissionDo permission = permissionService.getByUrl(actionUrl);
        if(subject.isPermitted(permission.getPermissionMessage())){
            return true;
        }
        return false;
    }
}
