package wmyskxz.blog.module.vo;

/**
 * 后台页面角色的权限数据模型
 *
 * @auth:wmyskxz
 * @date:2019/05/01 - 19:32
 */
public class AdminRolePermissionVo {
    private Long permissionId;// 权限id
    private String description;// 权限显示的名称
    private Boolean isHave;// 是否拥有该权限

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getHave() {
        return isHave;
    }

    public void setHave(Boolean have) {
        isHave = have;
    }
}
