package wmyskxz.blog.module.entity;

import java.util.Date;

public class Notify {
    private Long id;

    private Date createTime;

    private Long senderId;

    private Long recevierId;

    private String type;

    private Boolean isRead;

    public Notify(Long id, Date createTime, Long senderId, Long recevierId, String type, Boolean isRead) {
        this.id = id;
        this.createTime = createTime;
        this.senderId = senderId;
        this.recevierId = recevierId;
        this.type = type;
        this.isRead = isRead;
    }

    public Notify() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }
}