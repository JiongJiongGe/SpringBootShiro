package com.shiro.sevice.impl;

import com.shiro.entity.RoleDo;
import com.shiro.mapper.RoleMapper;
import com.shiro.sevice.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/4/13
 * @Time: 下午8:40
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据ids获取对象
     *
     * @param ids
     * @return
     */
    @Override
    public List<RoleDo> findList(List<Integer> ids){
        return roleMapper.queryList(ids);
    }

}
