package wmyskxz.blog.module.vo;

import java.util.Date;

/**
 * 用于与前台交互的点赞数据模型
 *
 * @auth:wmyskxz
 * @date:2019/03/14 - 16:23
 */
public class VoteVo {
    // UI显示的数据
    private String avatar;// 用户头像地址
    private String username;// 用户自定义名称
    private String blogTitle;// 点赞博客的标题
    private Date createTime;// 点赞时间

    // UI隐藏的数据
    private Long userId;// 点赞用户的id
    private Long blogId;// 点赞博客的id

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

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }
}
