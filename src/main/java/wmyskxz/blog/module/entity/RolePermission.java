package wmyskxz.blog.module.entity;

import java.util.Date;

public class RolePermission {
    private Long id;

    private Date createTime;

    private Date updateTime;

    private Long roleId;

    private Long permissionId;

    public RolePermission(Long id, Date createTime, Date updateTime, Long roleId, Long permissionId) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    public RolePermission() {
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}