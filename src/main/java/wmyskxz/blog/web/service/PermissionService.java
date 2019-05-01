package wmyskxz.blog.web.service;

import wmyskxz.blog.module.entity.Permission;
import wmyskxz.blog.module.vo.AdminRolePermissionVo;

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
     * @return
     */
    Long add(Permission permission);

    /**
     * 给角色赋予权限
     *
     * @param roleId
     * @param permissionId
     */
    void givePermission(Long roleId, Long permissionId);

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
    void update(Permission permission, Long permissionId);

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
     * 编辑角色时返回该角色拥有的权限数据模型
     * 简单表示：xxx1(√),xxx2(√),xxx3(×)
     *
     * @param roleId
     * @return
     */
    List<AdminRolePermissionVo> listByRoleIdForEdit(Long roleId);

    /**
     * 获取某一个用户的所有权限信息(Shiro)
     *
     * @param userId
     * @return
     */
    Set<String> listPermsByUserId(Long userId);

    /**
     * 获取某一个用户的所有权限信息(后台)
     *
     * @param userId
     * @return
     */
    List<Permission> listByUserId(Long userId);
}
