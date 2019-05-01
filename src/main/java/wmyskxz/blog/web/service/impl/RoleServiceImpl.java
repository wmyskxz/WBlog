package wmyskxz.blog.web.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Resource RoleMapper roleMapper;
    @Resource UserRoleMapper userRoleMapper;
    @Resource RolePermissionMapper rolePermissionMapper;
    @Resource PermissionMapper permissionMapper;

    @Override
    @Transactional// 开启事务
    public Long add(String name, String description) {
        Role role = new Role();
        role.setName(name);
        role.setDescription(description);
        roleMapper.insertSelective(role);
        return role.getId();
    }

    @Override
    @Transactional// 开启事务
    public void giveRole(Long userId, Long roleId) {
        // 查询,用户已有该角色就不管,没有就添加上
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.or().andUserIdEqualTo(userId).andRoleIdEqualTo(roleId);
        if (userRoleMapper.selectByExample(userRoleExample).isEmpty()) {
            // 没有则添加
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoleMapper.insertSelective(userRole);
        }   // end if
    }

    @Override
    @Transactional// 开启事务
    public void giveRoles(Long userId, Long... roleIds) {
        // 先删除所有的,然后再重新赋予
        UserRoleExample userRoleExample;
        for (Long roleId : roleIds) {
            userRoleExample = new UserRoleExample();
            userRoleExample.or().andUserIdEqualTo(userId).andRoleIdEqualTo(roleId);
            userRoleMapper.deleteByExample(userRoleExample);
            giveRole(userId, roleId);
        }
    }

    @Override
    @Transactional// 开启事务
    public void deleteByName(String name) {
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
    public void deleteById(Long roleId) {
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
    public void deleteByIds(Long... roleIds) {
        for (Long roleId : roleIds) {
            deleteById(roleId);
        }   // end for
    }

    @Override
    @Transactional// 开启事务
    public void update(String name, String description, Long roleId) {
        Role role = new Role();
        role.setName(name);
        role.setDescription(description);
        role.setId(roleId);
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    @Transactional// 开启事务
    public List<Role> listAll(int pageNum, int pageSize) {
        List<Role> resultList = new LinkedList<>();

        RoleExample roleExample = new RoleExample();
        roleExample.or();// 无条件查询即查询所有
        PageHelper.startPage(pageNum, pageSize);// 只对下一次查询有效
        resultList = roleMapper.selectByExample(roleExample);

        return resultList;
    }

    @Override
    @Transactional// 开启事务
    public Set<String> listByUserId(Long userId) {
        Set<String> resultList = new LinkedHashSet<>();

        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.or().andUserIdEqualTo(userId);
        List<UserRole> userRoleList = userRoleMapper.selectByExample(userRoleExample);

        // 拼接数据
        for (UserRole userRole : userRoleList) {
            resultList.add(roleMapper.selectByPrimaryKey(userRole.getRoleId()).getName());
        }   // end for

        return resultList;
    }

    @Override
    @Transactional// 开启事务
    public List<Role> listByUsername(String username) {
        return null;
    }
}
