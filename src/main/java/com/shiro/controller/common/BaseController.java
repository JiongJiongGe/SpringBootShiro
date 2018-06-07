package com.shiro.controller.common;

import com.google.gson.Gson;
import com.shiro.config.shiro.bean.SavedRequestBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/6/5
 * @Time: 上午9:55
 */
public class BaseController {

    private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 获取request body中的参数，如果通过shiro重登录，则从上一个请求中获取
     *
     * @param request
     * @return
     */
    protected Map<String, Object> getRequestParams(HttpServletRequest request){
        Map<String, Object> reqParams = new HashMap<String, Object>();
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        SavedRequestBean savedRequestBean = (SavedRequestBean) session.getAttribute("savedRequestBean");
        if(savedRequestBean != null){
            reqParams = savedRequestBean.getParams();
            session.removeAttribute("savedRequestBean");
        } else{
            Enumeration em = request.getParameterNames();
            //获取post请求body中请求的参数
            while (em.hasMoreElements()) {
                String name = (String) em.nextElement();
                String value = request.getParameter(name);
                reqParams.put(name, value);
            }
        }
        return reqParams;
    }
}
