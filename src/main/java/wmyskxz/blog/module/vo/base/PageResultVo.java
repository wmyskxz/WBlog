package wmyskxz.blog.module.vo.base;

import java.util.List;

/**
 * 用于与前台交互的表格数据模型
 *
 * @auth:wmyskxz
 * @date:2019/03/02 - 14:35
 */
public class PageResultVo {

    private List rows;
    private Long total;

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public PageResultVo(List rows, Long total) {
        this.total = total;
        this.rows = rows;
    }
}
