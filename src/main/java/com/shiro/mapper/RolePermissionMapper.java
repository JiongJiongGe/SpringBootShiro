package com.shiro.mapper;

import com.shiro.entity.RolePermissionDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RolePermissionMapper {

    List<RolePermissionDo> queryByRoleId(@Param("roleId") Integer roleId);
}
