package wmyskxz.blog.module.vo;

/**
 * 用于与前台交互的用户主页信息数据
 *
 * @auth:wmyskxz
 * @date:2019/03/15 - 15:46
 */
public class UserHomeVo {

    // UI显示的数据
    private String avatar;// 用户头像地址
    private String name;// 用户自定义名称
    private String description;// 自我介绍
    private int followSize;// 关注人数
    private int fanSize;// 粉丝数量
    private int blogSize;// 博客数量
    private int voteSize;// 点赞数量

    // UI隐藏的数据
    private Long userId;// 用户id
    private Boolean isFollow;// 是否关注该用户

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFollowSize() {
        return followSize;
    }

    public void setFollowSize(int followSize) {
        this.followSize = followSize;
    }

    public int getFanSize() {
        return fanSize;
    }

    public void setFanSize(int fanSize) {
        this.fanSize = fanSize;
    }

    public int getBlogSize() {
        return blogSize;
    }

    public void setBlogSize(int blogSize) {
        this.blogSize = blogSize;
    }

    public int getVoteSize() {
        return voteSize;
    }

    public void setVoteSize(int voteSize) {
        this.voteSize = voteSize;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getFollow() {
        return isFollow;
    }

    public void setFollow(Boolean follow) {
        isFollow = follow;
    }
}
