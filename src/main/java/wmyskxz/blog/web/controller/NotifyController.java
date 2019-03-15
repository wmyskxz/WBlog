package wmyskxz.blog.web.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wmyskxz.blog.module.vo.base.PageResultVo;
import wmyskxz.blog.module.vo.base.ResponseVo;
import wmyskxz.blog.util.ResultUtil;
import wmyskxz.blog.web.service.NotifyService;
import wmyskxz.blog.web.service.UserService;

/**
 * 通知消息控制器
 *
 * @auth:wmyskxz
 * @date:2019/02/28 - 19:10
 */
@RestController// 返回JSON
@RequestMapping("/apis/notify")
public class NotifyController {

    @Autowired NotifyService notifyService;
    @Autowired UserService userService;

    // 管理员发送一条通知
    @ApiOperation("管理员发送一条通知")
    @PostMapping("")
    public ResponseVo sendNotify() {
        return ResultUtil.success("发送成功!");
    }

    // 查询某个用户的通知消息
    @ApiOperation(value = "查询某个用户的通知消息", notes = "包括评论/私信/喜欢/关注四类消息")
    @GetMapping("/{userId}")
    public ResponseVo listByUserId(@PathVariable Long userId) {
        return ResultUtil.success("查询成功!", notifyService.countByUserId(userId));
    }

    // 查询某个用户的粉丝列表
    @ApiOperation("查询某个用户的粉丝列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "开始页面", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, dataType = "int")})
    @GetMapping("/fans/{userId}")
    public PageResultVo listFansByUserId(@PathVariable Long userId, @RequestParam int pageNum,
                                         @RequestParam int pageSize) {
        return ResultUtil.table(notifyService.listUserFansByUserId(userId, pageNum, pageSize),
                                notifyService.countUserFansByUserId(userId));
    }

    // 查询某个用户的关注用户列表
    @ApiOperation("查询某个用户的关注用户列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "开始页面", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, dataType = "int")})
    @GetMapping("/follows/{userId}")
    public PageResultVo listFollowsByUserId(@PathVariable Long userId, @RequestParam int pageNum,
                                            @RequestParam int pageSize) {
        return ResultUtil.table(notifyService.listUserFollowsByUserId(userId, pageNum, pageSize),
                                notifyService.countUserFollowsByUserId(userId));
    }

    // 查询某个用户的点赞通知信息
    @ApiOperation("查询某个用户的点赞通知信息")
    @GetMapping("/vote/{userId}")
    public PageResultVo listVotesByUserId(@PathVariable Long userId, @RequestParam int pageNum,
                                          @RequestParam int pageSize) {
        return ResultUtil.table(notifyService.listUserVotesByUserId(userId, pageNum, pageSize),
                                notifyService.countUserVotesByUserId(userId));
    }

    // 查询某个用户的关注通知消息
    @ApiOperation("查询某个用户的关注通知消息")
    @GetMapping("/follow/{userId}")
    public PageResultVo listFollowsNotifyByUserId(@PathVariable Long userId, @RequestParam int pageNum,
                                                  @RequestParam int pageSize) {
        return ResultUtil.table(notifyService.listUserFollowNotifyByUserId(userId, pageNum, pageSize),
                                notifyService.countUserFansByUserId(userId));
    }

    // 某一个用户给某一篇博文点赞
    @ApiOperation("某一个用户给某一篇博文点赞")
    @PostMapping("/vote/{userId}/{blogId}")
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
