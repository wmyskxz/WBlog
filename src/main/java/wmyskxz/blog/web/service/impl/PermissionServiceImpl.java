package wmyskxz.blog.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wmyskxz.blog.module.dao.PermissionMapper;
import wmyskxz.blog.module.dao.RolePermissionMapper;
import wmyskxz.blog.module.dao.UserRoleMapper;
import wmyskxz.blog.module.entity.*;
import wmyskxz.blog.web.service.PermissionService;

import javax.annotation.Resource;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * PermissionService实现类
 *
 * @auth:wmyskxz
 * @date:2019/02/28 - 13:03
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource PermissionMapper permissionMapper;
    @Resource RolePermissionMapper rolePermissionMapper;
    @Resource UserRoleMapper userRoleMapper;

    @Override
    @Transactional// 开启事务
    public Long add(Permission permission) {
        permissionMapper.insertSelective(permission);
        return permission.getId();
    }

    @Override
    @Transactional// 开启事务
    public void givePermission(Long roleId, Long permissionId) {
        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        rolePermissionExample.or().andRoleIdEqualTo(roleId).andPermissionIdEqualTo(permissionId);
        if (rolePermissionMapper.selectByExample(rolePermissionExample).isEmpty()) {
            // 如果没有则添加,有则不进行操作
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissionMapper.insertSelective(rolePermission);
        }   // end if
    }

    @Override
    @Transactional// 开启事务
    public void deleteById(Long permissionId) {
        permissionMapper.deleteByPrimaryKey(permissionId);
    }

    @Override
    @Transactional// 开启事务
    public void deleteByIds(Long... permissionIds) {

    }

    @Override
    @Transactional// 开启事务
    public void update(Permission permission, Long permissionId) {
        permission.setId(permissionId);
        permissionMapper.updateByPrimaryKeySelective(permission);
    }

    @Override
    @Transactional// 开启事务
    public List<Permission> listAll() {

        List<Permission> resultList;

        PermissionExample permissionExample = new PermissionExample();
        permissionExample.or();// 无条件查询即查询全部
        resultList = permissionMapper.selectByExample(permissionExample);

        return resultList;
    }

    @Override
    @Transactional// 开启事务
    public List<Permission> listByRoleId(Long roleId) {

        List<Permission> resultList = new LinkedList<>();

        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        rolePermissionExample.or().andRoleIdEqualTo(roleId);
        List<RolePermission> rolePermissionList = rolePermissionMapper.selectByExample(rolePermissionExample);
        for (RolePermission rolePermission : rolePermissionList) {
            resultList.add(permissionMapper.selectByPrimaryKey(rolePermission.getPermissionId()));
        }   // end for

        return resultList;
    }

    @Override
    @Transactional// 开启事务
    public Set<String> listPermsByUserId(Long userId) {

        Set<String> resultList = new LinkedHashSet<>();

        List<UserRole> userRoleList;
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.or().andUserIdEqualTo(userId);
        userRoleList = userRoleMapper.selectByExample(userRoleExample);

        for (UserRole userRole : userRoleList) {
            List<Permission> permissions = listByRoleId(userRole.getRoleId());
            for (Permission permission : permissions) {
                resultList.add(permission.getName());
            }
        }   // end for

        return resultList;
    }

    @Override
    @Transactional// 开启事务
    public List<Permission> listByUserId(Long userId) {
        List<Permission> resultList = new LinkedList<>();

        List<UserRole> userRoleList;
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.or().andUserIdEqualTo(userId);
        userRoleList = userRoleMapper.selectByExample(userRoleExample);

        for (UserRole userRole : userRoleList) {
            List<Permission> permissions = listByRoleId(userRole.getRoleId());
            for (Permission permission : permissions) {
                resultList.add(permission);
            }
        }   // end for

        return resultList;
    }
}
