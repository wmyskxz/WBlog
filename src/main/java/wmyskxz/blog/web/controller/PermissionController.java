package wmyskxz.blog.web.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wmyskxz.blog.config.PageConfig;
import wmyskxz.blog.module.entity.Permission;
import wmyskxz.blog.module.vo.base.PageResultVo;
import wmyskxz.blog.module.vo.base.ResponseVo;
import wmyskxz.blog.util.ConstCode;
import wmyskxz.blog.util.ResultUtil;
import wmyskxz.blog.web.service.PermissionService;
import wmyskxz.blog.web.service.RoleService;
import wmyskxz.blog.web.service.UserService;

import java.util.List;

/**
 * 权限管理相关控制器
 *
 * @auth:wmyskxz
 * @date:2019/03/01 - 08:42
 */
@RestController// 返回JSOn
@RequestMapping("/apis/admin/")
public class PermissionController {

    @Autowired UserService userService;
    @Autowired RoleService roleService;
    @Autowired PermissionService permissionService;

    // 增加一个角色
    @ApiOperation("增加一个角色")
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "角色名称,如:admin", required = true,
            dataType = "String"), @ApiImplicitParam(name = "description", value = "角色描述,UI显示,如:管理员", required = true,
            dataType = "String")})
    @PostMapping("/role")
    public ResponseVo addRole(@RequestParam String name, @RequestParam String description) {
        return ResultUtil.success("添加成功!", roleService.add(name, description));
    }

    // 增加一条权限信息
    @ApiOperation("增加一条权限信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "权限名称,如:user:list", required = true, dataType =
            "String"), @ApiImplicitParam(name = "description", value = "权限描述,UI显示,如:查询所有用户", required = true,
            dataType = "String"), @ApiImplicitParam(name = "url", value = "权限地址," + "如:/apis/user/", required = true,
            dataType = "String"), @ApiImplicitParam(name = "icon", value = "权限图标," + "默认使用awesome图标库", required =
            true, dataType = "String"),})
    @PostMapping("/permission")
    public ResponseVo addPermission(@RequestParam String name, @RequestParam String description,
                                    @RequestParam String url, @RequestParam String icon) {
        Permission permission = new Permission();
        permission.setName(name);
        permission.setDescription(description);
        permission.setUrl(url);
        permission.setIcon(icon);
        return ResultUtil.success("添加成功!", permissionService.add(permission));
    }

    // 给用户赋予角色
    @ApiOperation("给用户赋予角色")
    @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "Long")
    @PostMapping("/role/{userId}")
    public ResponseVo giveUserRoles(@PathVariable Long userId, @RequestParam Long roleId) {
        roleService.giveRole(userId, roleId);
        return ResultUtil.success("操作成功!");
    }

    // 给角色赋予权限
    @ApiOperation("给角色赋予权限")
    @ApiImplicitParam(name = "permissionId", value = "权限id", required = true, dataType = "Long")
    @PostMapping("/permission/{roleId}")
    public ResponseVo giveRolePermissions(@PathVariable Long roleId, @RequestParam Long permissionId) {
        permissionService.givePermission(roleId, permissionId);
        return ResultUtil.success("操作成功!");
    }

    // 删除一个角色
    @ApiOperation("删除一个角色")
    @DeleteMapping("/role/{roleId}")
    public ResponseVo deleteRoleByRoleId(@PathVariable Long roleId) {
        roleService.deleteById(roleId);
        return ResultUtil.success("操作成功!");
    }

    // 删除一条权限
    @ApiOperation("删除一条权限")
    @DeleteMapping("/permission/{permissionId}")
    public ResponseVo deletePermissionByPermissionId(@PathVariable Long permissionId) {
        permissionService.deleteById(permissionId);
        return ResultUtil.success("操作成功!");
    }

    // 修改一个角色(包括权限)
    @ApiOperation("修改一个角色(包括权限)")
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "角色名称,如:admin", required = true,
            dataType = "String"), @ApiImplicitParam(name = "description", value = "角色描述,UI显示,如:管理员", required = true,
            dataType = "String"), @ApiImplicitParam(name = "permissionIds", value = "选中的权限的id集合", required = true,
            dataType = "Long[]")})
    @PutMapping("/role/{roleId}")
    public ResponseVo updateRole(@PathVariable Long roleId, @RequestParam String name, @RequestParam String description,
                                 @RequestParam Long[] permissionIds) {
        roleService.update(name, description, roleId, permissionIds);
        return ResultUtil.success("操作成功!");
    }

    // 修改一条权限信息
    @ApiOperation("修改一条权限信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "权限名称,如:user:list", required = true, dataType =
            "String"), @ApiImplicitParam(name = "description", value = "权限描述,UI显示,如:查询所有用户", required = true,
            dataType = "String"), @ApiImplicitParam(name = "url", value = "权限地址," + "如:/apis/user/", required = true,
            dataType = "String"), @ApiImplicitParam(name = "icon", value = "权限图标," + "默认使用awesome图标库", required =
            true, dataType = "String")})
    @PutMapping("permission/{permissionId}")
    public ResponseVo updatePermission(@PathVariable Long permissionId, Permission permission) {
        permissionService.update(permission, permissionId);
        return ResultUtil.success("操作成功!");
    }

    // 查询所有角色信息
    @ApiOperation("查询所有角色信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "开始页面", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, dataType = "int")})
    @GetMapping("/role")
    public PageResultVo listRoles(@RequestParam(defaultValue = PageConfig.PAGE_NUM) int pageNum,
                                  @RequestParam(defaultValue = PageConfig.PAGE_SIZE) int pageSize) {
        return ResultUtil.table(roleService.listAll(pageNum, pageSize), ConstCode.DEFAULT_NO_PAGING);
    }

    // 查询一个用户所拥有的角色信息
    @ApiOperation("查询一个用户所拥有的角色信息")
    @GetMapping("/role/{userId}")
    public PageResultVo listRolesByUserId(@PathVariable Long userId) {
        return ResultUtil.table((List<?>) roleService.listByUserId(userId), ConstCode.DEFAULT_NO_PAGING);
    }

    // 查询所有权限信息
    @ApiOperation("查询所有权限信息")
    @GetMapping("/permission")
    public PageResultVo listPermissions() {
        return ResultUtil.table(permissionService.listAll(), ConstCode.DEFAULT_NO_PAGING);
    }

    // 查询一个角色拥有的权限信息，用于侧边栏
    @ApiOperation("查询一个角色拥有的权限信息，用于侧边栏")
    @GetMapping("/permission/{roleId}")
    public PageResultVo listPermissionsByRoleId(@PathVariable Long roleId) {
        return ResultUtil.table(permissionService.listByRoleId(roleId), ConstCode.DEFAULT_NO_PAGING);
    }

    // 编辑一个用户时返回的角色信息
    @ApiOperation("编辑一个用户时返回的角色信息")
    @GetMapping("/user/{userId}/edit")
    public PageResultVo editUser(@PathVariable Long userId) {
        return ResultUtil.table(roleService.listByUserIdForEdit(userId), ConstCode.DEFAULT_NO_PAGING);
    }

    // 编辑一个角色时返回的权限信息
    @ApiOperation("编辑一个角色时返回的权限信息")
    @GetMapping("/permission/{roleId}/edit")
    public PageResultVo editRole(@PathVariable Long roleId) {
        return ResultUtil.table(permissionService.listByRoleIdForEdit(roleId), ConstCode.DEFAULT_NO_PAGING);
    }

    // 查询一个用户所拥有的权限信息
    @ApiOperation("查询一个用户所拥有的权限信息")
    @GetMapping("/permission/user/{userId}")
    public PageResultVo listPermissionsByUserId(@PathVariable Long userId) {
        return ResultUtil.table(permissionService.listByUserId(userId), ConstCode.DEFAULT_NO_PAGING);
    }

    // 修改角色权限
    @ApiOperation("修改角色权限")
    @PutMapping("/user/{userId}")
    public ResponseVo updateUser(@PathVariable Long userId, @RequestParam Long[] roleIds) {
        roleService.giveRoles(userId, roleIds);
        return ResultUtil.success("成功修改!");
    }
}
