package wmyskxz.blog.module.vo;

import java.util.Date;

/**
 * 用于与前台交互的博客信息(主页显示,带用户信心)的数据模型
 *
 * @auth:wmyskxz
 * @date:2019/02/26 - 14:24
 */
public class BlogListVo {

    // UI显示的数据
    private String title;// 文章标题
    private String summary;// 文章简介
    private String avatar;// 作者头像地址
    private String username;// 作者自定义名称
    private int voteSize;// 喜欢数
    private int commentSize;// 评论数
    private int readSize;// 阅读数
    private Date createTime;// 创建时间
    private Boolean isVote;// 是否已经点赞

    // UI隐藏的数据
    private Long userId;// 用户对应的主键id
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getVote() {
        return isVote;
    }

    public void setVote(Boolean vote) {
        isVote = vote;
    }
}
