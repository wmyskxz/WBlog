package wmyskxz.blog.module.entity;

import java.util.Date;

public class BlogCategory {
    private Long id;

    private Date createTime;

    private Date updateTime;

    private Long categoryId;

    private Long blogId;

    public BlogCategory(Long id, Date createTime, Date updateTime, Long categoryId, Long blogId) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.categoryId = categoryId;
        this.blogId = blogId;
    }

    public BlogCategory() {
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }
}