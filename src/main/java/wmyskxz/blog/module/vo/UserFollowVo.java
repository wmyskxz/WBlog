package wmyskxz.blog.module.vo;

/**
 * 用于与前台交互的用户关注数据模型
 *
 * @auth:wmyskxz
 * @date:2019/02/28 - 08:55
 */
public class UserFollowVo extends UserInfoVo {

    /**
     * 包含UserInfo中的基础信息
     */

    // UI显示的数据
    private boolean isFollowed;// 是否关注该用户

    public boolean isFollowed() {
        return isFollowed;
    }

    public void setFollowed(boolean followed) {
        isFollowed = followed;
    }
}
