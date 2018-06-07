package com.shiro.sevice;

import com.shiro.entity.UserRoleDo;

import java.util.List;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/4/13
 * @Time: 下午8:42
 */
public interface UserRoleService {

    /**
     * 根据用户Id和角色Id获取对象
     *
     * @param userId  用户Id
     * @return
     */
    List<UserRoleDo> getByUserId(Integer userId);
}
