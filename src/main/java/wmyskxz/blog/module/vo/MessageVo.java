package wmyskxz.blog.module.vo;

import java.util.Date;

/**
 * 用于与前台交互的私信消息数据模型
 *
 * @auth:wmyskxz
 * @date:2019/02/27 - 16:58
 */
public class MessageVo {
    // UI显示的数据
    private String avatar;// 用户头像地址
    private String content;// 聊天内容
    private Date createTime;// 发送时间
    // UI隐藏的数据
    private Long userId;// 发送该条消息的用户id

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
