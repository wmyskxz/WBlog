package wmyskxz.blog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wmyskxz.blog.module.vo.base.ResponseVo;

/**
 * 评论控制器
 *
 * @auth:wmyskxz
 * @date:2019/02/28 - 18:59
 */
@Controller
@RequestMapping("/apis/comment")
public class CommentController {
    // 评论(增加一条评论)
    @PostMapping("")
    public ResponseVo addComment() {
        return null;
    }

    // 回复评论
    @PostMapping("/reply/{commentId}")
    public ResponseVo replyComment(@PathVariable Long commentId) {
        return null;
    }

    // 删除一条评论(只有博主才有权限删除)
    @DeleteMapping("/{commentId}")
    public ResponseVo deleteComment(@PathVariable Long commentId) {
        return null;
    }

    // 查询某篇文章下的所有评论信息
    @GetMapping("/{blogId}")
    public ResponseVo listAllByBlogId(@PathVariable Long blogId) {
        return null;
    }

    // 查询某个用户的评论信息
    @GetMapping("/user/{userId}")
    public ResponseVo listAllByUserId(@PathVariable Long userId) {
        return null;
    }
}
