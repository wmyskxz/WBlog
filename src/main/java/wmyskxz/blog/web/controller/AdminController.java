package wmyskxz.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wmyskxz.blog.module.vo.base.PageResultVo;
import wmyskxz.blog.module.vo.base.ResponseVo;
import wmyskxz.blog.util.ResultUtil;
import wmyskxz.blog.web.service.AdminService;
import wmyskxz.blog.web.service.UserService;

/**
 * 后台控制器，提供后台相关接口
 *
 * @auth:wmyskxz
 * @date:2019/03/16 - 11:39
 */
@RestController// 返回JSON格式
@RequestMapping("/apis/admin/")
public class AdminController {

    // 用户相关(包括Shiro踢人、查看在线人数的接口)

    // 权限在PermissionController中定义

    // 应该..没了吧..

    @Autowired AdminService adminService;
    @Autowired UserService userService;

    @GetMapping("")
    public ResponseVo home() {
        return ResultUtil.success("获取成功!", adminService.home());
    }

    @GetMapping("/online")
    public PageResultVo online() {
        return ResultUtil.table(userService.selectOnlineUsers(), (long) 10);
    }
}
