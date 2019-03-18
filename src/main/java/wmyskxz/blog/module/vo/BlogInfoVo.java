package wmyskxz.blog.module.vo;

import java.util.Date;

/**
 * 用于与前台交互的数据模型
 *
 * @auth:wmyskxz
 * @date:2019/02/26 - 13:25
 */
public class BlogInfoVo {
    // UI显示的数据
    private String title;// 文章标题
    private String summary;// 文章简介
    private String headPicture;// 题图图片地址
    private int voteSize;// 喜欢数
    private int commentSize;// 评论数
    private int readSize;// 阅读数
    private Date createTime;// 创建时间

    // UI隐藏的数据
    private Long blogId;// 博文id

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public int getVoteSize() {
        return voteSize;
    }

    public void setVoteSize(int voteSize) {
        this.voteSize = voteSize;
    }

    public int getCommentSize() {
        return commentSize;
    }

    public void setCommentSize(int commentSize) {
        this.commentSize = commentSize;
    }

    public int getReadSize() {
        return readSize;
    }

    public void setReadSize(int readSize) {
        this.readSize = readSize;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
