package wmyskxz.blog.module.vo;

import java.util.Date;

/**
 * 用于与前台交互的评论数据模型
 *
 * @auth:wmyskxz
 * @date:2019/02/27 - 15:19
 */
public class CommentVo {

    // UI要显示的数据
    private String avatar;// 用户头像地址
    private String username;// 用户自定义名称
    private Date createTime;// 评论时间
    private String content;// 评论内容
    private String atUsername;// @用户的用户名称
    private String blogTitle;// 评论文章的标题

    // UI隐藏的数据
    private Long userId;// 评论用户的主键id
    private Long blogId;// 评论文章的id
    private Long atId;// @用户的用户id
    private Long commentId;// 评论id

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAtUsername() {
        return atUsername;
    }

    public void setAtUsername(String atUsername) {
        this.atUsername = atUsername;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAtId() {
        return atId;
    }

    public void setAtId(Long atId) {
        this.atId = atId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }
}
