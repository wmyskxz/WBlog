package wmyskxz.blog.module.entity;

import java.util.Date;

public class Message {
    private Long id;

    private Date createTime;

    private Long notifyId;

    private String content;

    private Boolean isDeleteBySender;

    private Boolean isDeleteByRecevier;

    private Long senderId;

    private Long recevierId;

    public Message(Long id, Date createTime, Long notifyId, String content, Boolean isDeleteBySender, Boolean isDeleteByRecevier, Long senderId, Long recevierId) {
        this.id = id;
        this.createTime = createTime;
        this.notifyId = notifyId;
        this.content = content;
        this.isDeleteBySender = isDeleteBySender;
        this.isDeleteByRecevier = isDeleteByRecevier;
        this.senderId = senderId;
        this.recevierId = recevierId;
    }

    public Message() {
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

    public Long getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(Long notifyId) {
        this.notifyId = notifyId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Boolean getIsDeleteBySender() {
        return isDeleteBySender;
    }

    public void setIsDeleteBySender(Boolean isDeleteBySender) {
        this.isDeleteBySender = isDeleteBySender;
    }

    public Boolean getIsDeleteByRecevier() {
        return isDeleteByRecevier;
    }

    public void setIsDeleteByRecevier(Boolean isDeleteByRecevier) {
        this.isDeleteByRecevier = isDeleteByRecevier;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getRecevierId() {
        return recevierId;
    }

    public void setRecevierId(Long recevierId) {
        this.recevierId = recevierId;
    }
}