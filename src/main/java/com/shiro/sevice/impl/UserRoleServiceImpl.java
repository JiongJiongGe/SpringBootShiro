package com.shiro.sevice.impl;

import com.shiro.entity.UserRoleDo;
import com.shiro.mapper.UserRoleMapper;
import com.shiro.sevice.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/4/13
 * @Time: 下午8:42
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 根据用户Id和角色Id获取对象
     *
     * @param userId  用户Id
     * @return
     */
    @Override
    public List<UserRoleDo> getByUserId(Integer userId){
        return userRoleMapper.queryByUserId(userId);
    }
}
