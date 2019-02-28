package wmyskxz.blog.module.entity;

import java.util.Date;

public class UserRole {
    private Long id;

    private Date createTime;

    private Date updateTime;

    private Long userId;

    private Long roleId;

    public UserRole(Long id, Date createTime, Date updateTime, Long userId, Long roleId) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.userId = userId;
        this.roleId = roleId;
    }

    public UserRole() {
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}