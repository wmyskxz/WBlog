package wmyskxz.blog.web.service;

import wmyskxz.blog.module.entity.Permission;

import java.util.List;
import java.util.Set;

/**
 * 权限相关Service
 *
 * @auth:wmyskxz
 * @date:2019/02/28 - 12:59
 */
public interface PermissionService {
    /**
     * 添加权限信息
     *
     * @param permission
     */
    void add(Permission permission);

    /**
     * 通过id删除权限信息
     *
     * @param permissionId
     */
    void deleteById(Long permissionId);

    /**
     * 批量删除权限信息
     *
     * @param permissionIds
     */
    void deleteByIds(Long... permissionIds);

    /**
     * 更新权限信息
     *
     * @param permission
     * @param permissionId
     */
    void updateById(Permission permission, Long permissionId);

    /**
     * 返回所有权限信息
     *
     * @return
     */
    List<Permission> listAll();

    /**
     * 获取某个角色的所有权限
     *
     * @param roleId
     * @return
     */
    List<Permission> listByRoleId(Long roleId);

    /**
     * 获取某一个用户的所有权限信息
     *
     * @param userId
     * @return
     */
    Set<String> listPermsByUserId(Long userId);



}
