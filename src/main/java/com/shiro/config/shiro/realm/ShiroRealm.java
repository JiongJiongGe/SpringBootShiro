package com.shiro.config.shiro.realm;

import com.shiro.entity.*;
import com.shiro.sevice.*;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 安全数据源，设置用户的角色和权限
 */
public class ShiroRealm extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 授权权限验证
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        try {
            UserDo userDo = (UserDo) principals.getPrimaryPrincipal();
            //获取用户角色关系
            List<UserRoleDo> userRoleDos = userRoleService.getByUserId(userDo.getId());
            List<Integer> roleIds = new ArrayList<Integer>();
            if (!CollectionUtils.isEmpty(userRoleDos)) {
                for (UserRoleDo userRoleDo : userRoleDos) {
                    roleIds.add(userRoleDo.getRoleId());
                }
            }
            //获取用户角色信息
            List<RoleDo> roleDos = roleService.findList(roleIds);
            if (!CollectionUtils.isEmpty(roleDos)) {
                for (RoleDo roleDo : roleDos) {
                    authorizationInfo.addRole(roleDo.getRole());
                    //获取角色权限
                    List<RolePermissionDo> rolePermissionDos = rolePermissionService.findByRoleId(roleDo.getId());
                    List<Integer> rpIds = new ArrayList<Integer>();
                    if (!CollectionUtils.isEmpty(rolePermissionDos)) {
                        for (RolePermissionDo rolePermissionDo : rolePermissionDos) {
                            rpIds.add(rolePermissionDo.getPermissId());
                        }
                    }
                    List<PermissionDo> permissionDos = permissionService.findList(rpIds);
                    if (!CollectionUtils.isEmpty(permissionDos)) {
                        for (PermissionDo permissionDo : permissionDos) {
                            authorizationInfo.addStringPermission(permissionDo.getPermissionMessage());
                        }
                    }
                }
            }
        }catch (Exception e){
            logger.error("doGetAuthorizationInfo error = {}", e);
        }
        return authorizationInfo;
    }

    /**
     * 验证用户是否认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //获取传入的用户ID.
        String userID = (String)token.getPrincipal();
        //根据用户ID获取对象，若存在。则验证成功
        UserDo userDo = userService.getByID(userID);
        if(userDo == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userDo, "", getName());
        return authenticationInfo;
    }

}