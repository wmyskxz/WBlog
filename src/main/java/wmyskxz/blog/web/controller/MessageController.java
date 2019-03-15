package wmyskxz.blog.web.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wmyskxz.blog.module.vo.base.PageResultVo;
import wmyskxz.blog.module.vo.base.ResponseVo;
import wmyskxz.blog.util.ConstCode;
import wmyskxz.blog.util.ResultUtil;
import wmyskxz.blog.web.service.MessageService;

/**
 * 私信控制器
 *
 * @auth:wmyskxz
 * @date:2019/02/28 - 19:04
 */
@RestController// 返回JSON
@RequestMapping("/apis/message")
public class MessageController {

    @Autowired MessageService messageService;

    // 给某个用户发送一条私信
    @ApiOperation("给某个用户发送一条私信")
    @ApiImplicitParams({@ApiImplicitParam(name = "receiverId", value = "接受者id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "content", value = "私信内容", required = true, dataType = "String"),})
    @PostMapping("/{userId}")
    public ResponseVo sendMessage(@PathVariable Long userId, Long receiverId, String content) {
        System.out.println(receiverId);
        System.out.println(content);
        messageService.sendMessage(userId, receiverId, content);
        return ResultUtil.success("发送成功!");
    }

    // 删除与某一个用户的私信列表
    @ApiOperation("删除与某一个用户的私信列表")
    @DeleteMapping("/{userId}/{counterpartId}")
    public ResponseVo deleteMessage(@PathVariable Long userId, @PathVariable Long counterpartId) {
        messageService.deleteMessageListByUserIdAndCounterpartId(userId, counterpartId);
        return ResultUtil.success("删除成功!");
    }

    // 删除用户的所有的私信列表
    @ApiOperation("删除用户的所有的私信列表")
    @DeleteMapping("/{userId}")
    public ResponseVo deleteAllMessages(@PathVariable Long userId) {
        messageService.deleteAllMessageListByUserId(userId);
        return ResultUtil.success("删除成功!");
    }

    // 查询某个用户的私信列表
    @ApiOperation("查询某个用户的私信列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "开始页面", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, dataType = "int")})
    @GetMapping("/{userId}")
    public PageResultVo listByUserId(@PathVariable Long userId, @RequestParam int pageNum, @RequestParam int pageSize) {
        return ResultUtil.table(messageService.listByUserId(userId), ConstCode.DEFAULT_NO_PAGING);
    }

    // 查询与某个用户之间的私信记录
    @ApiOperation("查询与某个用户之间的私信纪录")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "开始页面", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, dataType = "int")})
    @GetMapping("/{userId}/{counterpartId}")
    public PageResultVo listByCounterpartId(@PathVariable Long userId, @PathVariable Long counterpartId,
                                            @RequestParam int pageNum, @RequestParam int pageSize) {
        return ResultUtil
                .table(messageService.listMessageListByUserIdAndConterpartId(userId, counterpartId, pageNum, pageSize),
                       messageService.countByUserIdAndCounterpartId(userId, counterpartId));
    }
}
