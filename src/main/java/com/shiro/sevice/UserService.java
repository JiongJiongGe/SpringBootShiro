package com.shiro.sevice;

import com.shiro.entity.UserDo;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/4/13
 * @Time: 下午8:18
 */
public interface UserService {

    /**
     * 通过ID获取对象
     *
     * @param id
     * @return
     */
    public UserDo getByID(String id);
}
