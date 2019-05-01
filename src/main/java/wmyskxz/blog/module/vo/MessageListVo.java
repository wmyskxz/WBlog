package wmyskxz.blog.module.vo;

import java.util.Date;

/**
 * 用于与前端交互的私信消息列表数据模型
 *
 * @auth:wmyskxz
 * @date:2019/02/27 - 16:48
 */
public class MessageListVo {
    // UI显示的数据
    private String avatar;// 用户头像地址
    private String name;// 用户自定义名称
    private Date lastChatTime;// 最后一次聊天时间
    private String lastChatContent;// 最后一次说话的内容

    // UI隐藏的数据
    private Long counterpartId;// 聊天对方的id

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public Date getLastChatTime() {
        return lastChatTime;
    }

    public void setLastChatTime(Date lastChatTime) {
        this.lastChatTime = lastChatTime;
    }

    public String getLastChatContent() {
        return lastChatContent;
    }

    public void setLastChatContent(String lastChatContent) {
        this.lastChatContent = lastChatContent;
    }

    public Long getCounterpartId() {
        return counterpartId;
    }

    public void setCounterpartId(Long counterpartId) {
        this.counterpartId = counterpartId;
    }
}
