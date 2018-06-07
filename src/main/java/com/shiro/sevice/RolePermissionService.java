package com.shiro.sevice;

import com.shiro.entity.RolePermissionDo;

import java.util.List;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/4/13
 * @Time: 下午8:41
 */
public interface RolePermissionService {

    /**
     * 根据roleId获取数据
     *
     * @param roleId 角色Id
     * @return
     */
    public List<RolePermissionDo> findByRoleId(Integer roleId);
}
