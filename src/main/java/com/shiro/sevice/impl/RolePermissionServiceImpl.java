package com.shiro.sevice.impl;

import com.shiro.entity.RolePermissionDo;
import com.shiro.mapper.RolePermissionMapper;
import com.shiro.sevice.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/4/13
 * @Time: 下午8:41
 */
@Service("rolePermissionService")
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    /**
     * 根据roleId获取数据
     *
     * @param roleId 角色Id
     * @return
     */
    @Override
    public List<RolePermissionDo> findByRoleId(Integer roleId){
        return rolePermissionMapper.queryByRoleId(roleId);
    }
}
