package wmyskxz.blog.web.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wmyskxz.blog.module.vo.base.ResponseVo;
import wmyskxz.blog.util.ResultUtil;
import wmyskxz.blog.web.service.UserService;

/**
 * 用户相关控制器
 *
 * @auth:wmyskxz
 * @date:2019/03/01 - 22:48
 */
@Controller
@RequestMapping("/apis/user")
public class UserController {

    @Autowired UserService userService;

    // 增加一个用户(后台)
    @ApiOperation("增加一个用户(后台)")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "用户账号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "email", value = "用户邮箱", required = true, dataType = "String")})
    @PostMapping("")
    public ResponseVo add(String username, String password, String email) {
        userService.register(username, password, email);
        return ResultUtil.success("添加成功!");
    }

    // 删除一个用户
    @DeleteMapping("/{userId}")
    public ResponseVo deleteById(@PathVariable Long userId) {
        return null;
    }

    // 修改一个用户(后台)
    @PutMapping("/{userId}")
    public ResponseVo update(@PathVariable Long userId) {
        return null;
    }

    // 查询所有用户的用户信息
    @GetMapping("")
    public ResponseVo list() {
        return null;
    }

    // 查询某一个用户的信息
    @GetMapping("/{userId}")
    public ResponseVo find(@PathVariable Long userId) {
        return null;
    }
}
