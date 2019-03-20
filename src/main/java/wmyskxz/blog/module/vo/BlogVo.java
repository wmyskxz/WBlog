package wmyskxz.blog.module.vo;

import java.util.Date;

/**
 * 用于与前台交互的单篇博客数据模型
 *
 * @auth:wmyskxz
 * @date:2019/02/26 - 14:38
 */
public class BlogVo {

    // 用于UI显示的数据
    private String title;// 文章标题
    private String avatar;// 作者头像地址
    private String name;// 作者自定义名称
    private int voteSize;// 喜欢数
    private int commentSize;// 评论数
    private int readSize;// 阅读数
    private String contentHtml;// html版的内容
    private Date createTime;// 文章创建时间

    // UI隐藏的数据
    private Long userId;// 用户id
    private Long blogId;// 博文id

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
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

