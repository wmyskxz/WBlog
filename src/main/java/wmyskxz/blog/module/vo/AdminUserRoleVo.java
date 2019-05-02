package wmyskxz.blog.module.vo;

/**
 * 编辑用户时显示的角色数据模型
 *
 * @auth:wmyskxz
 * @date:2019/05/01 - 22:09
 */
public class AdminUserRoleVo {
    private Long RoleId;// 角色id
    private String roleName;// 角色名称
    private Boolean isHave;// 用户是否拥有该角色

    public Long getRoleId() {
        return RoleId;
    }

    public void setRoleId(Long roleId) {
        RoleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Boolean getHave() {
        return isHave;
    }

    public void setHave(Boolean have) {
        isHave = have;
    }
}
