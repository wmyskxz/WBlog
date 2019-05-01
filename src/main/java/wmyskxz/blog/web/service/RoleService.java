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
    Long add(String name, String description);

    /**
     * 给用户赋予一个角色
     *
     * @param userId
     * @param roleId
     */
    void giveRole(Long userId, Long roleId);

    /**
     * 为用户赋予/删除多个角色
     *
     * @param userId
     * @param roleIds
     */
    void giveRoles(Long userId, Long... roleIds);

    /**
     * 通过角色名称来删除角色信息
     *
     * @param name
     */
    void deleteByName(String name);

    /**
     * 通过角色id来删除角色信息
     *
     * @param roleId
     */
    void deleteById(Long roleId);

    /**
     * 批量删除角色,通过ids
     *
     * @param roleIds
     */
    void deleteByIds(Long... roleIds);

    /**
     * 更新角色信息
     *  @param name
     * @param description
     * @param roleId
     * @param permissionIds
     */
    void update(String name, String description, Long roleId, Long[] permissionIds);

    /**
     * 返回所有的角色信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Role> listAll(int pageNum, int pageSize);

    /**
     * 通过角色id来查询相应的角色信息
     *
     * @param userId
     * @return
     */
    Set<String> listByUserId(Long userId);

    /**
     * 通过角色登录账号来查询相应的角色信息
     *
     * @param username
     * @return
     */
    List<Role> listByUsername(String username);
}
