package wmyskxz.blog.module.entity;

import java.util.Date;

public class Vote {
    private Long id;

    private Date createTime;

    private Long userId;

    private Long blogId;

    private Long notifyId;

    public Vote(Long id, Date createTime, Long userId, Long blogId, Long notifyId) {
        this.id = id;
        this.createTime = createTime;
        this.userId = userId;
        this.blogId = blogId;
        this.notifyId = notifyId;
    }

    public Vote() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(Long notifyId) {
        this.notifyId = notifyId;
    }
}