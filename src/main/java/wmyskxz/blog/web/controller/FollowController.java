package wmyskxz.blog.web.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wmyskxz.blog.module.vo.base.ResponseVo;
import wmyskxz.blog.util.ResultUtil;
import wmyskxz.blog.web.service.UserService;

/**
 * 关注相关控制器
 *
 * @auth:wmyskxz
 * @date:2019/03/18 - 04:38
 */
@RestController// 返回JSON
@RequestMapping("/apis/")
public class FollowController {

    @Autowired UserService userService;

    // 某一个用户关注另一个用户
    @ApiOperation("某一个用户关注另一个用户")
    @PostMapping("/follow/{userId}/{followUserId}")
    public ResponseVo follow(@PathVariable Long userId, @PathVariable Long followUserId) {
        userService.follow(userId, followUserId);
        return ResultUtil.success("关注成功!");
    }

    // 某一个用户取消对一个用户的关注
    @ApiOperation("某一个用户取消对一个用户的关注")
    @PostMapping("/unfollow/{userId}/{followUserId}")
    public ResponseVo unFollow(@PathVariable Long userId, @PathVariable Long followUserId) {
        userService.unFollow(userId, followUserId);
        return ResultUtil.success("成功取消关注");
    }
}
