package wmyskxz.blog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wmyskxz.blog.module.vo.base.ResponseVo;

/**
 * 权限管理相关控制器
 *
 * @auth:wmyskxz
 * @date:2019/03/01 - 08:42
 */
@Controller
@RequestMapping("/apis/psermission")
public class PermissionController {
    // 增加一个角色
    @PostMapping("/role")
    public ResponseVo addRole() {
        return null;
    }

    // 增加一条权限信息
    @PostMapping("/permission")
    public ResponseVo addPermission() {
        return null;
    }

    // 给用户赋予角色
    @PostMapping("/role/{userId}")
    public ResponseVo giveUserRoles(@PathVariable Long userId) {
        return null;
    }

    // 给角色赋予权限
    @PostMapping("/permission/{roleId}")
    public ResponseVo giveRolePermissions(@PathVariable Long roleId) {
        return null;
    }

    // 删除一个角色
    @DeleteMapping("/role/{roleId}")
    public ResponseVo deleteRoleByRoleId(@PathVariable Long roleId) {
        return null;
    }

    // 删除一条权限
    @DeleteMapping("/permission/{permissionId}")
    public ResponseVo deletePermissionByPermissionId(@PathVariable Long permissionId) {
        return null;
    }

    // 修改一个角色
    @PutMapping("/role/{roleId}")
    public ResponseVo updateRole(@PathVariable Long roleId) {
        return null;
    }

    // 修改一条权限信息
    @PutMapping("permission/{permissionId}")
    public ResponseVo updatePermission(@PathVariable Long permissionId) {
        return null;
    }

    // 查询所有角色信息
    @GetMapping("/role")
    public ResponseVo listRoles() {
        return null;
    }

    // 查询一个用户所拥有的角色信息
    @GetMapping("/role/{userId}")
    public ResponseVo listRolesByUserId(@PathVariable Long userId) {
        return null;
    }

    // 查询所有权限信息
    @GetMapping("/permission/")
    public ResponseVo listPermissions() {
        return null;
    }

    // 查询一个角色拥有的权限信息
    @GetMapping("/permission/{roleId}")
    public ResponseVo listPermissionsByRoleId(@PathVariable Long roleId) {
        return null;
    }

    // 查询一个用户所拥有的权限信息
    @GetMapping("/permission/user/{userId}")
    public ResponseVo listPermissionsByUserId(@PathVariable Long userId) {
        return null;
    }
}
