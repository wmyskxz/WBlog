package wmyskxz.blog.module.entity;

import java.util.Date;

public class UserFollow {
    private Long id;

    private Date createTime;

    private Long userId;

    private Long followUserId;

    public UserFollow(Long id, Date createTime, Long userId, Long followUserId) {
        this.id = id;
        this.createTime = createTime;
        this.userId = userId;
        this.followUserId = followUserId;
    }

    public UserFollow() {
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

    public Long getFollowUserId() {
        return followUserId;
    }

    public void setFollowUserId(Long followUserId) {
        this.followUserId = followUserId;
    }
}