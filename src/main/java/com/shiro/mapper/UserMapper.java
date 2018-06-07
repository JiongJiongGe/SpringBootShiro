package com.shiro.mapper;

import com.shiro.entity.UserDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/4/13
 * @Time: 下午8:17
 */
@Mapper
@Component
public interface UserMapper {

    UserDo queryByID(@Param("id") String id);
}
