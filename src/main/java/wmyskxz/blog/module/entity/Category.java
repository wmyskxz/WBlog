package wmyskxz.blog.module.entity;

import java.util.Date;

public class Category {
    private Long id;

    private Date createTime;

    private Date updateTime;

    private Long userId;

    private String name;

    public Category(Long id, Date createTime, Date updateTime, Long userId, String name) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.userId = userId;
        this.name = name;
    }

    public Category() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}