package wmyskxz.blog.module.entity;

import javax.persistence.Transient;
import java.util.Date;

public class User {
    private Long id;

    private String email;

    private String name;

    private String username;

    private Date createTime;

    private Date updateTime;

    private String password;

    private String salt;

    private String avatar;

    private String description;

    private Integer followSize;

    private Integer voteSize;

    private Integer fanSize;

    private Date lastLoginTime;

    private Boolean status;

    /** 登录ip */
    @Transient
    private String loginIpAddress;

    public User(Long id, String email, String name, String username, Date createTime, Date updateTime, String password, String salt, String avatar, String description, Integer followSize, Integer voteSize, Integer fanSize, Date lastLoginTime, Boolean status) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.username = username;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.password = password;
        this.salt = salt;
        this.avatar = avatar;
        this.description = description;
        this.followSize = followSize;
        this.voteSize = voteSize;
        this.fanSize = fanSize;
        this.lastLoginTime = lastLoginTime;
        this.status = status;
    }

    public User() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getFollowSize() {
        return followSize;
    }

    public void setFollowSize(Integer followSize) {
        this.followSize = followSize;
    }

    public Integer getVoteSize() {
        return voteSize;
    }

    public void setVoteSize(Integer voteSize) {
        this.voteSize = voteSize;
    }

    public Integer getFanSize() {
        return fanSize;
    }

    public void setFanSize(Integer fanSize) {
        this.fanSize = fanSize;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getLoginIpAddress() {
        return loginIpAddress;
    }

    public void setLoginIpAddress(String loginIpAddress) {
        this.loginIpAddress = loginIpAddress;
    }
}