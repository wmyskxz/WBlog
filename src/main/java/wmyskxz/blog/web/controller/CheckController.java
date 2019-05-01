package wmyskxz.blog.web.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wmyskxz.blog.module.vo.base.ResponseVo;
import wmyskxz.blog.util.ResultUtil;
import wmyskxz.blog.web.service.BlogService;
import wmyskxz.blog.web.service.CategoryService;
import wmyskxz.blog.web.service.UserService;

/**
 * 检查是否可用的控制器
 *
 * @auth:wmyskxz
 * @date:2019/03/16 - 21:35
 */
@RestController// 返回JSON
@RequestMapping("/apis/check")
public class CheckController {

    @Autowired UserService userService;
    @Autowired BlogService blogService;
    @Autowired CategoryService categoryService;

    // 检查用户名是否可用
    @ApiOperation("检查用户名是否可用")
    @ApiImplicitParam(name = "name", value = "用户账号", required = true, dataType = "String")
    @PostMapping("username")
    public ResponseVo checkUsername(String username) {
        Boolean flag = userService.checkUsername(username);
        if (flag.equals(true)) {
            return ResultUtil.success("登录账号可用");
        } else return ResultUtil.error("登录账号已被使用");
    }

    // 检查自定义名称是否可用
    @ApiOperation("检查自定义名称是否可用")
    @ApiImplicitParam(name = "name", value = "用户自定义名称", required = true, dataType = "String")
    @PostMapping("name")
    public ResponseVo checkName(String name) {
        Boolean flag = userService.checkUserName(name);
        if (flag.equals(true)) {
            return ResultUtil.success("用户名可用");
        } else return ResultUtil.error("用户名已被使用");
    }

    // 检查email是否可用
    @ApiOperation("检查email是否可用")
    @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String")
    @PostMapping("email")
    public ResponseVo checkEmail(String email) {
        Boolean flag = userService.checkEmail(email);
        if (flag.equals(true)) {
            return ResultUtil.success("邮箱可用");
        } else return ResultUtil.error("邮箱已被使用");
    }

    // 检查博客标题是否可用
    @ApiOperation("检查博客标题是否可用")
    @ApiImplicitParam(name = "title", value = "博客标题", required = true, dataType = "String")
    @PostMapping("title")
    public ResponseVo checkTitle(String title) {
        Boolean flag = blogService.checkTitle(title);
        if (flag.equals(true)) {
            return ResultUtil.success("标题可用");
        } else return ResultUtil.error("标题不可用");
    }

    // 检查分类名称是否可用
    @ApiOperation("检查分类名称是否可用(不允许同一用户创建相同名称的分类)")
    @ApiImplicitParam(name = "categoryName", value = "分类名称", required = true, dataType = "String")
    @PostMapping("categoryName")
    public ResponseVo checkCategoryName(String categoryName) {
        Boolean flag = categoryService.checkCategoryName(categoryName);
        if (flag.equals(true)) {
            return ResultUtil.success("分类名称不可用");
        } else return ResultUtil.error("分类名称可用");
    }
}
