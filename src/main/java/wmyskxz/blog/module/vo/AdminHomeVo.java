package wmyskxz.blog.module.vo;

import java.util.List;

/**
 * 后台管理数据统计页的数据模型
 *
 * @auth:wmyskxz
 * @date:2019/04/10 - 08:17
 */
public class AdminHomeVo {
    private Integer totalUsers;// 总共的用户数
    private Integer addUsers;// 新增的用户数
    private Integer totalBlogs;// 总共的文章数
    private Integer addBlogs;// 新增的文章数
    private List<AdminHomeChartVo> chartVos;// 走势图数据

    public Integer getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(Integer totalUsers) {
        this.totalUsers = totalUsers;
    }

    public Integer getAddUsers() {
        return addUsers;
    }

    public void setAddUsers(Integer addUsers) {
        this.addUsers = addUsers;
    }

    public Integer getTotalBlogs() {
        return totalBlogs;
    }

    public void setTotalBlogs(Integer totalBlogs) {
        this.totalBlogs = totalBlogs;
    }

    public Integer getAddBlogs() {
        return addBlogs;
    }

    public void setAddBlogs(Integer addBlogs) {
        this.addBlogs = addBlogs;
    }

    public List<AdminHomeChartVo> getChartVos() {
        return chartVos;
    }

    public void setChartVos(List<AdminHomeChartVo> chartVos) {
        this.chartVos = chartVos;
    }
}
