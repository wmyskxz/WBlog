package wmyskxz.blog.web.service.impl;

import org.springframework.stereotype.Service;
import wmyskxz.blog.module.entity.Role;
import wmyskxz.blog.web.service.RoleService;

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
    @Override
    public void addRole(String name, String description) {

    }

    @Override
    public void deleteRoleByRoleName(String name) {

    }

    @Override
    public void deleteRoleByRoleId(Long roleId) {

    }

    @Override
    public void deleteRolesByRoleId(Long... roleIds) {

    }

    @Override
    public void updateRoleByRoleId(String name, String description, Long roleId) {

    }

    @Override
    public List<Role> listAll() {
        return null;
    }

    @Override
    public Set<String> getRolesByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Role> getRolesByUserName(String name) {
        return null;
    }
}
