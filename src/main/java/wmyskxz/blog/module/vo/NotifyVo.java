package wmyskxz.blog.module.vo;

/**
 * 用于与前台交互的通知消息的数据模型
 *
 * @auth:wmyskxz
 * @date:2019/02/28 - 08:49
 */
public class NotifyVo {
    // UI显示的数据
    private int unreadCommentSize;// 未读评论消息数量
    private int unreadMessageSize;// 未读私信消息数量
    private int unreadVoteSize;// 未读点赞消息数量
    private int unreadFollowSize;// 未读关注消息数量

    public int getUnreadCommentSize() {
        return unreadCommentSize;
    }

    public void setUnreadCommentSize(int unreadCommentSize) {
        this.unreadCommentSize = unreadCommentSize;
    }

    public int getUnreadMessageSize() {
        return unreadMessageSize;
    }

    public void setUnreadMessageSize(int unreadMessageSize) {
        this.unreadMessageSize = unreadMessageSize;
    }

    public int getUnreadVoteSize() {
        return unreadVoteSize;
    }

    public void setUnreadVoteSize(int unreadVoteSize) {
        this.unreadVoteSize = unreadVoteSize;
    }

    public int getUnreadFollowSize() {
        return unreadFollowSize;
    }

    public void setUnreadFollowSize(int unreadFollowSize) {
        this.unreadFollowSize = unreadFollowSize;
    }
}
