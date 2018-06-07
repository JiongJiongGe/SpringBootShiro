package com.shiro.sevice;

import com.shiro.entity.RoleDo;

import java.util.List;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/4/13
 * @Time: 下午8:40
 */
public interface RoleService {

    /**
     * 根据ids获取对象
     *
     * @param ids
     * @return
     */
    public List<RoleDo> findList(List<Integer> ids);
}
