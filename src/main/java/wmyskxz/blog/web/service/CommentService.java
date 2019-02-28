package wmyskxz.blog.web.service;

import wmyskxz.blog.module.vo.CommentVo;

import java.util.List;

/**
 * 评论相关Service
 *
 * @auth:wmyskxz
 * @date:2019/02/26 - 16:03
 */
public interface CommentService {

    /**
     * 增加评论(登录之后)
     *
     * @param userId
     * @param blogId
     * @param content
     * @param atId
     */
    void addComment(Long userId, Long blogId, String content, Long atId);

    /**
     * 通过commentId删除一条评论消息(默认权限只有博主才有)
     *
     * @param commentId
     */
    void deleteCommentByCommentId(Long commentId);

    /**
     * 博文页 - 获取一篇博文下面的评论列表
     *
     * @param blogId
     * @return
     */
    List<CommentVo> getCommentsByBlogId(Long blogId);

    /**
     * 个人消息页 -  获取某一个用户的所有评论消息
     *
     * @param userId
     * @return
     */
    List<CommentVo> getCommentsByUserId(Long userId);
}
