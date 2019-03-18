package wmyskxz.blog.module.vo;

import java.util.Date;

/**
 * 用于与前台交互的博客编辑页的数据模型
 *
 * @auth:wmyskxz
 * @date:2019/03/18 - 12:47
 */
public class BlogEditVo {

    // UI显示的数据
    private String title;// 文章标题
    private String headPicture;// 文章题图
    private String contentMd;// 文章md源码
    private String contentHtml;// 文章转义之后的html代码
    private String summary;//文章简介
    private Boolean isRecommend;// 文章是否被推荐
    private Date createTime;// 创建时间
    private Date updateTime;// 更新时间

    // UI隐藏的数据
    private Long categoryId;// 文章的分类id

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public String getContentMd() {
        return contentMd;
    }

    public void setContentMd(String contentMd) {
        this.contentMd = contentMd;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Boolean getRecommend() {
        return isRecommend;
    }

    public void setRecommend(Boolean recommend) {
        isRecommend = recommend;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
}
