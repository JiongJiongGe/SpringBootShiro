package com.shiro.mapper;

import com.shiro.entity.RoleDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/4/13
 * @Time: 下午8:36
 */
@Mapper
@Component
public interface RoleMapper {

    List<RoleDo> queryList(@Param("ids") List<Integer> ids);
}
