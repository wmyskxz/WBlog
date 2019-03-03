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
    void add(Long userId, Long blogId, String content, Long atId);

    /**
     * 回复一条评论(登录之后)
     *
     * @param userId
     * @param blogId
     * @param content
     * @param atId
     * @param blogerId 博主id
     */
    void reply(Long userId, Long blogId, String content, Long atId, Long blogerId);

    /**
     * 通过commentId删除一条评论消息(默认权限只有博主才有)
     *
     * @param commentId
     */
    void deleteById(Long commentId);

    /**
     * 博文页 - 获取一篇博文下面的评论列表
     *
     * @param blogId
     * @return
     */
    List<CommentVo> listByBlogId(Long blogId);

    /**
     * 个人消息页 -  获取某一个用户的所有评论消息
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<CommentVo> listByUserId(Long userId, int pageNum, int pageSize);

    /**
     * 查询一个用户的评论消息数
     *
     * @param userId
     * @return
     */
    Long countByUserId(Long userId);
}
