package com.shiro.mapper;

import com.shiro.entity.UserRoleDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/4/13
 * @Time: 下午8:37
 */
@Mapper
@Component
public interface UserRoleMapper {

    List<UserRoleDo> queryByUserId(@Param("userId") Integer userId);
}
