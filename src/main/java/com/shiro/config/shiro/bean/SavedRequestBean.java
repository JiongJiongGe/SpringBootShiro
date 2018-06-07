package com.shiro.config.shiro.bean;

import org.apache.shiro.web.util.SavedRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/6/3
 * @Time: 下午11:05
 */
public class SavedRequestBean extends SavedRequest{

    private static Logger logger = LoggerFactory.getLogger(SavedRequestBean.class);

    private Map<String, Object> params;

    public SavedRequestBean(HttpServletRequest request, Map<String, Object> params) {
        super(request);
        this.params = params;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
