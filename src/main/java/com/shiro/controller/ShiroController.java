package com.shiro.controller;

import com.shiro.response.ErrorCode;
import com.shiro.response.RpcResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置些shiro权限控制相关的控制器
 */

@RestController
@RequestMapping("/shiro")
public class ShiroController {

    /**
     * 权限不足
     *
     * @return
     */
    @RequestMapping("/no/permission")
    @ResponseBody
    public RpcResult<String> noPermission(){
        return RpcResult.ofError(ErrorCode.BIZ_ERROR.getCode(), ErrorCode.BIZ_ERROR.getMsg("权限不足"));
    }
}
