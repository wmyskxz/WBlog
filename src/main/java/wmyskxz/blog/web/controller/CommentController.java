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
import wmyskxz.blog.web.service.CommentService;

/**
 * 评论控制器
 *
 * @auth:wmyskxz
 * @date:2019/02/28 - 18:59
 */
@RestController
@RequestMapping("/apis/comment")
public class CommentController {

    @Autowired CommentService commentService;

    // 评论(增加一条评论)
    @ApiOperation("增加一条评论")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "评论者的id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "blogId", value = "评论的文章id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "content", value = "评论内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "atId", value = "评论者@的人的id,非必须", required = false, dataType = "Long"),})
    @PostMapping("")
    public ResponseVo addComment(Long userId, Long blogId, String content, Long atId) {
        commentService.add(userId, blogId, content, atId);
        return ResultUtil.success("评论成功!");
    }

    // 回复评论
    @ApiOperation("回复评论")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "评论者的id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "blogId", value = "评论的文章id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "content", value = "评论内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "atId", value = "评论者@的人的id,必须", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "blogerId", value = "博主id", required = true, dataType = "Long"),})
    @PostMapping("/reply/")
    public ResponseVo replyComment(Long userId, Long blogId, String content, Long atId, Long blogerId) {
        commentService.reply(userId, blogId, content, atId, blogerId);
        return ResultUtil.success("回复成功!");
    }

    // 删除一条评论(只有博主才有权限删除)
    @ApiOperation(value = "删除一条评论", notes = "只有博主才有权限删除")
    @DeleteMapping("/{commentId}")
    public ResponseVo deleteComment(@PathVariable Long commentId) {
        commentService.deleteById(commentId);
        return ResultUtil.success("删除成功!");
    }

    // 查询某篇文章下的所有评论信息
    @ApiOperation(value = "查询某篇文章下的所有评论消息", notes = "默认显示全部,不分页")
    @GetMapping("/{blogId}")
    public PageResultVo listAllByBlogId(@PathVariable Long blogId) {
        return ResultUtil.table(commentService.listByBlogId(blogId), ConstCode.DEFAULT_NO_PAGING);
    }

    // 查询某个用户的评论信息
    @ApiOperation("查询某个用户的评论信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "开始页面", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, dataType = "int")})
    @GetMapping("/user/{userId}")
    public PageResultVo listAllByUserId(@PathVariable Long userId,
                                        @RequestParam(defaultValue = PageConfig.PAGE_NUM) int pageNum,
                                        @RequestParam(defaultValue = PageConfig.PAGE_SIZE) int pageSize) {
        return ResultUtil
                .table(commentService.listByUserId(userId, pageNum, pageSize), commentService.countByUserId(userId));
    }
}
