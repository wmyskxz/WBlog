package wmyskxz.blog.web.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wmyskxz.blog.config.PageConfig;
import wmyskxz.blog.module.dao.PermissionMapper;
import wmyskxz.blog.module.dao.RoleMapper;
import wmyskxz.blog.module.dao.RolePermissionMapper;
import wmyskxz.blog.module.dao.UserRoleMapper;
import wmyskxz.blog.module.entity.*;
import wmyskxz.blog.web.service.RoleService;

import javax.annotation.Resource;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * RoleService实现类
 *
 * @auth:wmyskxz
 * @date:2019/02/28 - 12:38
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    RoleMapper roleMapper;
    @Resource
    UserRoleMapper userRoleMapper;
    @Resource
    RolePermissionMapper rolePermissionMapper;
    @Resource
    PermissionMapper permissionMapper;

    @Override
    @Transactional// 开启事务
    public void addRole(String name, String description) {
        Role role = new Role();
        role.setName(name);
        role.setDescription(description);
        roleMapper.insertSelective(role);
    }

    @Override
    @Transactional// 开启事务
    public void deleteRoleByRoleName(String name) {
        // 如果还有用户属于该角色则不允许删除
        RoleExample roleExample = new RoleExample();
        roleExample.or().andNameEqualTo(name);
        Role role = roleMapper.selectByExample(roleExample).get(0);
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.or().andRoleIdEqualTo(role.getId());
        if (userRoleMapper.selectByExample(userRoleExample).isEmpty()) {
            // 没有用户属于该角色
            // 1.删除角色对应的相关权限
            RolePermissionExample rolePermissionExample = new RolePermissionExample();
            rolePermissionExample.or().andRoleIdEqualTo(role.getId());
            List<RolePermission> rolePermissionList = rolePermissionMapper.selectByExample(rolePermissionExample);
            for (RolePermission rolePermission : rolePermissionList) {
                permissionMapper.deleteByPrimaryKey(rolePermission.getPermissionId());
            }   // end for:删除了与角色相关的所有权限

            // 2.删除角色信息
            roleMapper.deleteByPrimaryKey(role.getId());
        }   // end if:如果有用户属于该角色则不做任何处理
    }

    @Override
    @Transactional// 开启事务
    public void deleteRoleByRoleId(Long roleId) {
        // 如果还有用户属于该角色则不允许删除
        Role role = roleMapper.selectByPrimaryKey(roleId);
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.or().andRoleIdEqualTo(role.getId());
        if (userRoleMapper.selectByExample(userRoleExample).isEmpty()) {
            // 没有用户属于该角色
            // 1.删除角色对应的相关权限
            RolePermissionExample rolePermissionExample = new RolePermissionExample();
            rolePermissionExample.or().andRoleIdEqualTo(roleId);
            List<RolePermission> rolePermissionList = rolePermissionMapper.selectByExample(rolePermissionExample);
            for (RolePermission rolePermission : rolePermissionList) {
                permissionMapper.deleteByPrimaryKey(rolePermission.getPermissionId());
            }   // end for:删除了与角色相关的所有权限

            // 2.删除角色信息
            roleMapper.deleteByPrimaryKey(roleId);
        }   // end if:如果有用户属于该角色则不做任何处理
    }

    @Override
    @Transactional// 开启事务
    public void deleteRolesByRoleId(Long... roleIds) {
        for (Long roleId : roleIds) {
            deleteRoleByRoleId(roleId);
        }   // end for
    }

    @Override
    @Transactional// 开启事务
    public void updateRoleByRoleId(String name, String description, Long roleId) {
        Role role = new Role();
        role.setName(name);
        role.setDescription(description);
        role.setId(roleId);
        roleMapper.updateByPrimaryKey(role);
    }

    @Override
    @Transactional// 开启事务
    public List<Role> listAll() {
        List<Role> resultList = new LinkedList<>();

        RoleExample roleExample = new RoleExample();
        roleExample.or();// 无条件查询即查询所有
        PageHelper.startPage(PageConfig.PAGE_NUM, PageConfig.PAGE_SIZE);// 只对下一次查询有效
        resultList = roleMapper.selectByExample(roleExample);

        return resultList;
    }

    @Override
    @Transactional// 开启事务
    public Set<String> getRolesByUserId(Long userId) {
        Set<String> resultList = new LinkedHashSet<>();

        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.or().andUserIdEqualTo(userId);
        PageHelper.startPage(PageConfig.PAGE_NUM, PageConfig.PAGE_SIZE);// 只对下一次查询有效
        List<UserRole> userRoleList = userRoleMapper.selectByExample(userRoleExample);

        // 拼接数据
        for (UserRole userRole : userRoleList) {
            resultList.add(roleMapper.selectByPrimaryKey(userRole.getRoleId()).getName());
        }   // end for

        return resultList;
    }

    @Override
    @Transactional// 开启事务
    public List<Role> getRolesByUsername(String username) {
        return null;
    }
}
