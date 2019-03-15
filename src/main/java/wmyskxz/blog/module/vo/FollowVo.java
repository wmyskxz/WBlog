package wmyskxz.blog.module.vo;

import java.util.Date;

/**
 * 用于与前台交互的用户关注数据模型
 *
 * @auth:wmyskxz
 * @date:2019/03/15 - 09:04
 */
public class FollowVo {

    // UI显示的数据
    private String avatar;// 头像地址
    private String username;// 用户自定义名称
    private Date createTime;// 关注时间
    private boolean isFollow;// 是否关注

    // UI隐藏的数据
    private Long userId;// 关注用户id

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
