package wmyskxz.blog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wmyskxz.blog.module.vo.base.ResponseVo;

/**
 * 用户相关控制器
 *
 * @auth:wmyskxz
 * @date:2019/03/01 - 22:48
 */
@Controller
@RequestMapping("/apis/user")
public class UserController {

    // 增加一个用户(后台)
    @PostMapping("")
    public ResponseVo add() {
        return null;
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
