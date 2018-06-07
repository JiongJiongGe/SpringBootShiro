package com.shiro.sevice.impl;

import com.shiro.entity.PermissionDo;
import com.shiro.mapper.PermissionMapper;
import com.shiro.sevice.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/4/13
 * @Time: 下午8:38
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 通过ids获取对象
     *
     * @param ids
     * @return
     */
    @Override
    public List<PermissionDo> findList(List<Integer> ids){
        return permissionMapper.queryList(ids);
    }

    /**
     * 根据url获取对象
     *
     * @param url
     * @return
     */
    @Override
    public PermissionDo getByUrl(String url){
        return permissionMapper.queryByUrl(url);
    }
}
