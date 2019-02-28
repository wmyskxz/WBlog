package wmyskxz.blog.module.entity;

import java.util.Date;

public class BlogInfo {
    private Long id;

    private Date createTime;

    private Date updateTime;

    private Long userId;

    private String title;

    private String summary;

    private String headPicture;

    private Integer commentSize;

    private Integer readSize;

    private Integer voteSize;

    private Boolean isRecommend;

    public BlogInfo(Long id, Date createTime, Date updateTime, Long userId, String title, String summary, String headPicture, Integer commentSize, Integer readSize, Integer voteSize, Boolean isRecommend) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.userId = userId;
        this.title = title;
        this.summary = summary;
        this.headPicture = headPicture;
        this.commentSize = commentSize;
        this.readSize = readSize;
        this.voteSize = voteSize;
        this.isRecommend = isRecommend;
    }

    public BlogInfo() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture == null ? null : headPicture.trim();
    }

    public Integer getCommentSize() {
        return commentSize;
    }

    public void setCommentSize(Integer commentSize) {
        this.commentSize = commentSize;
    }

    public Integer getReadSize() {
        return readSize;
    }

    public void setReadSize(Integer readSize) {
        this.readSize = readSize;
    }

    public Integer getVoteSize() {
        return voteSize;
    }

    public void setVoteSize(Integer voteSize) {
        this.voteSize = voteSize;
    }

    public Boolean getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Boolean isRecommend) {
        this.isRecommend = isRecommend;
    }
}