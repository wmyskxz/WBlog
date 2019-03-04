package wmyskxz.blog.web.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wmyskxz.blog.module.vo.base.PageResultVo;
import wmyskxz.blog.module.vo.base.ResponseVo;
import wmyskxz.blog.util.ResultUtil;
import wmyskxz.blog.web.service.NotifyService;

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
    @GetMapping("/fans/{userId}")
    public PageResultVo listFansByUserId(@PathVariable Long userId) {
        return ResultUtil
                .table(notifyService.listUserFansByUserId(userId), notifyService.countUserFansByUserId(userId));
    }

    // 查询某个用户的关注用户列表
    @ApiOperation("查询某个用户的关注用户列表")
    @GetMapping("/follows/{userId}")
    public PageResultVo listFollowsByUserId(@PathVariable Long userId) {
        return ResultUtil
                .table(notifyService.listUserFollowsByUserId(userId), notifyService.countUserFollowsByUserId(userId));
    }
}
