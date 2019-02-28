package wmyskxz.blog.module.vo;

/**
 * 用于与前台交互的用户信息的数据模型
 *
 * @auth:wmyskxz
 * @date:2019/02/26 - 15:16
 */
public class UserInfoVo {

    // 用于UI显示的数据
    private String username;// 用户自定义的名称
    private String avatar;// 用户头像图片地址
    private int fanSize;// 用户粉丝数量
    private int followSize;// 用户关注数量
    private int voteSize;// 用户收获的喜欢数

    // UI隐藏的数据
    private Long userId;// 用户的主键id

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getFanSize() {
        return fanSize;
    }

    public void setFanSize(int fanSize) {
        this.fanSize = fanSize;
    }

    public int getFollowSize() {
        return followSize;
    }

    public void setFollowSize(int followSize) {
        this.followSize = followSize;
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
}
