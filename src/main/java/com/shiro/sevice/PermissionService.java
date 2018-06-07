package com.shiro.sevice;

import com.shiro.entity.PermissionDo;

import java.util.List;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/4/13
 * @Time: 下午8:38
 */
public interface PermissionService {

    /**
     * 通过ids获取对象
     *
     * @param ids
     * @return
     */
    List<PermissionDo> findList(List<Integer> ids);

    /**
     * 根据url获取对象
     *
     * @param url
     * @return
     */
    PermissionDo getByUrl(String url);
}
