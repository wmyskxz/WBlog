package wmyskxz.blog.module.entity;

import java.util.Date;

public class BlogContent {
    private Long id;

    private Date createTime;

    private Date updateTime;

    private String contentMd;

    private String contentHtml;

    private Long blogId;

    public BlogContent(Long id, Date createTime, Date updateTime, String contentMd, String contentHtml, Long blogId) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.contentMd = contentMd;
        this.contentHtml = contentHtml;
        this.blogId = blogId;
    }

    public BlogContent() {
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

    public String getContentMd() {
        return contentMd;
    }

    public void setContentMd(String contentMd) {
        this.contentMd = contentMd == null ? null : contentMd.trim();
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml == null ? null : contentHtml.trim();
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }
}