package com.shiro.sevice.impl;

import com.shiro.entity.UserDo;
import com.shiro.mapper.UserMapper;
import com.shiro.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/4/13
 * @Time: 下午8:19
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 通过ID获取对象
     *
     * @param id
     * @return
     */
    @Override
    public UserDo getByID(String id){
        return userMapper.queryByID(id);
    }
}
