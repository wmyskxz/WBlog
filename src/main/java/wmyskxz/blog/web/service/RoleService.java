package wmyskxz.blog.web.service;

import wmyskxz.blog.module.entity.Role;

import java.util.List;
import java.util.Set;

/**
 * Role相关服务
 *
 * @auth:wmyskxz
 * @date:2019/02/28 - 12:33
 */
public interface RoleService {
    /**
     * 增加角色信息
     *
     * @param name
     * @param description
     */
    void addRole(String name, String description);

    /**
     * 通过角色名称来删除角色信息
     *
     * @param name
     */
    void deleteRoleByRoleName(String name);

    /**
     * 通过角色id来删除角色信息
     *
     * @param roleId
     */
    void deleteRoleByRoleId(Long roleId);

    /**
     * 批量删除角色,通过ids
     *
     * @param roleIds
     */
    void deleteRolesByRoleId(Long... roleIds);

    /**
     * 更新角色信息
     *
     * @param name
     * @param description
     * @param roleId
     */
    void updateRoleByRoleId(String name, String description, Long roleId);

    /**
     * 返回所有的角色信息
     *
     * @return
     */
    List<Role> listAll();

    /**
     * 通过角色id来查询相应的角色信息
     *
     * @param userId
     * @return
     */
    Set<String> getRolesByUserId(Long userId);

    /**
     * 通过角色登录账号来查询相应的角色信息
     *
     * @param username
     * @return
     */
    List<Role> getRolesByUsername(String username);
}
