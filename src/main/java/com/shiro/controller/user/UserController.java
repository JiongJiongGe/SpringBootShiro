package com.shiro.controller.user;

import com.google.gson.Gson;
import com.shiro.controller.common.BaseController;
import com.shiro.response.RpcResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/web/in/user")
public class UserController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 用户查询
     *
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("user:list")//权限管理
    @ResponseBody
    public RpcResult<String> list(HttpServletRequest request){
        Integer userID = getUserId(request);
        Map<String, Object> queryParams = getRequestParams(request);
        logger.info("list queryParams = {}, userID = {}", new Gson().toJson(queryParams), userID);
        return RpcResult.ofSuccess("list");
    }

    /**
     * 用户添加
     *
     * @return
     */
    @RequestMapping("/add")
    @RequiresPermissions("user:add")//权限管理
    @ResponseBody
    public RpcResult<String> add(HttpServletRequest request){
        Integer userID = getUserId(request);
        Map<String, Object> queryParams = getRequestParams(request);
        logger.info("add queryParams = {}, userID = {}", new Gson().toJson(queryParams), userID);
        return RpcResult.ofSuccess("add");
    }

    /**
     * 用户删除
     *
     * @return
     */
    @RequestMapping("/del")
    @RequiresPermissions("user:del")//权限管理
    public RpcResult<String> del(HttpServletRequest request){
        Map<String, Object> queryParams = getRequestParams(request);
        logger.info("add queryParams = {}", new Gson().toJson(queryParams));
        return RpcResult.ofSuccess("del");
    }
}