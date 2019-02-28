package wmyskxz.blog.web.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import wmyskxz.blog.config.PageConfig;
import wmyskxz.blog.module.dao.PermissionMapper;
import wmyskxz.blog.module.entity.Permission;
import wmyskxz.blog.module.entity.PermissionExample;
import wmyskxz.blog.web.service.PermissionService;

import javax.annotation.Resource;
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

    @Resource
    PermissionMapper permissionMapper;

    @Override
    public void add(Permission permission) {

    }

    @Override
    public void deleteById(Long permissionId) {

    }

    @Override
    public void deleteByIds(Long... permissionIds) {

    }

    @Override
    public void updateById(Permission permission, Long permissionId) {

    }

    @Override
    public List<Permission> listAll() {
        List<Permission> resultList = new LinkedList<>();

        PermissionExample permissionExample = new PermissionExample();
        permissionExample.or();// 无条件查询即查询全部
        PageHelper.startPage(PageConfig.PAGE_NUM, PageConfig.PAGE_SIZE);// 只对下一行查询生效
        resultList = permissionMapper.selectByExample(permissionExample);

        return resultList;
    }

    @Override
    public List<Permission> listByRoleId(Long roleId) {
        return null;
    }

    @Override
    public Set<String> listPermsByUserId(Long userId) {
        return null;
    }
}
