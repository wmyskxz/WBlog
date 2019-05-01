package wmyskxz.blog.web.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wmyskxz.blog.config.PageConfig;
import wmyskxz.blog.module.vo.base.PageResultVo;
import wmyskxz.blog.module.vo.base.ResponseVo;
import wmyskxz.blog.util.ConstCode;
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
    public PageResultVo listFansByUserId(@PathVariable Long userId,
                                         @RequestParam(defaultValue = PageConfig.PAGE_NUM) int pageNum,
                                         @RequestParam(defaultValue = PageConfig.PAGE_SIZE) int pageSize) {
        return ResultUtil.table(notifyService.listUserFansByUserId(userId, pageNum, pageSize),
                                notifyService.countUserFansByUserId(userId));
    }

    // 查询某个用户的关注用户列表
    @ApiOperation("查询某个用户的关注用户列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "开始页面", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, dataType = "int")})
    @GetMapping("/follows/{userId}")
    public PageResultVo listFollowsByUserId(@PathVariable Long userId,
                                            @RequestParam(defaultValue = PageConfig.PAGE_NUM) int pageNum,
                                            @RequestParam(defaultValue = PageConfig.PAGE_SIZE) int pageSize) {
        return ResultUtil.table(notifyService.listUserFollowsByUserId(userId, pageNum, pageSize),
                                notifyService.countUserFollowsByUserId(userId));
    }

    // 查询某个用户的点赞通知信息
    @ApiOperation("查询某个用户的点赞通知信息")
    @GetMapping("/vote/{userId}")
    public PageResultVo listVotesByUserId(@PathVariable Long userId,
                                          @RequestParam(defaultValue = PageConfig.PAGE_NUM) int pageNum,
                                          @RequestParam(defaultValue = PageConfig.PAGE_SIZE) int pageSize) {
        return ResultUtil.table(notifyService.listUserVotesByUserId(userId, pageNum, pageSize),
                                notifyService.countUserVotesByUserId(userId));
    }

    // 查询某个用户的关注通知消息
    @ApiOperation("查询某个用户的关注通知消息")
    @GetMapping("/follow/{userId}")
    public PageResultVo listFollowsNotifyByUserId(@PathVariable Long userId,
                                                  @RequestParam(defaultValue = PageConfig.PAGE_NUM) int pageNum,
                                                  @RequestParam(defaultValue = PageConfig.PAGE_SIZE) int pageSize) {
        return ResultUtil.table(notifyService.listUserFollowNotifyByUserId(userId, pageNum, pageSize),
                                notifyService.countUserFansByUserId(userId));
    }

    // 已读所有未读评论消息
    @ApiOperation("已读所有未读评论消息")
    @PostMapping("/{userId}/comment/readAll")
    public ResponseVo readAllComments(@PathVariable Long userId) {
        notifyService.readAllByUserIdAndType(userId, ConstCode.NOTIFY_COMMENT_TYPE);
        return ResultUtil.success("成功!");
    }

    // 已读所有未读私信消息
    @ApiOperation("已读所有未读私信消息")
    @PostMapping("/{userId}/message/readAll")
    public ResponseVo readAllMessages(@PathVariable Long userId) {
        notifyService.readAllByUserIdAndType(userId, ConstCode.NOTIFY_MESSAGE_TYPE);
        return ResultUtil.success("成功!");
    }

    // 已读所有未读点赞消息
    @ApiOperation("已读所有未读点赞消息")
    @PostMapping("/{userId}/vote/readAll")
    public ResponseVo readAllVotes(@PathVariable Long userId) {
        notifyService.readAllByUserIdAndType(userId, ConstCode.NOTIFY_VOTE_TYPE);
        return ResultUtil.success("成功!");
    }

    // 已读所有未读关注消息
    @ApiOperation("已读所有未读关注消息")
    @PostMapping("/{userId}/follow/readAll")
    public ResponseVo readAllFollows(@PathVariable Long userId) {
        notifyService.readAllByUserIdAndType(userId, ConstCode.NOTIFY_FOLLOW_TYPE);
        return ResultUtil.success("成功!");
    }

}
