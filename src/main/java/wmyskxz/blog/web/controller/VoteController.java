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
 * 点赞相关控制器
 *
 * @auth:wmyskxz
 * @date:2019/03/16 - 21:44
 */
@RestController// 返回JSON
@RequestMapping("/apis/")
public class VoteController {

    @Autowired UserService userService;

    // 某一个用户给某一篇博文点赞
    @ApiOperation("某一个用户给某一篇博文点赞")
    @PostMapping("vote/{userId}/{blogId}")
    public ResponseVo vote(@PathVariable Long userId, @PathVariable Long blogId) {
        userService.vote(userId, blogId);
        return ResultUtil.success("成功!");
    }

    // 某一个用户取消对一篇博文的点赞
    @ApiOperation("某一个用户取消对一篇博文的点赞")
    @PostMapping("/unvote/{userId}/{blogId}")
    public ResponseVo unVote(@PathVariable Long userId, @PathVariable Long blogId) {
        userService.unVote(userId, blogId);
        return ResultUtil.success("成功取消点赞");
    }
}
