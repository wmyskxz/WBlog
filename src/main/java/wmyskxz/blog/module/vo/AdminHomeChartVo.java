package wmyskxz.blog.module.vo;

/**
 * 后台管理数据统计页走势图的数据模型
 *
 * @auth:wmyskxz
 * @date:2019/04/10 - 08:33
 */
public class AdminHomeChartVo {
    private String date;// 日期
    private Integer addUsers;// 新增用户数
    private Integer addBlogs;// 新增博客数

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getAddUsers() {
        return addUsers;
    }

    public void setAddUsers(Integer addUsers) {
        this.addUsers = addUsers;
    }

    public Integer getAddBlogs() {
        return addBlogs;
    }

    public void setAddBlogs(Integer addBlogs) {
        this.addBlogs = addBlogs;
    }
}
