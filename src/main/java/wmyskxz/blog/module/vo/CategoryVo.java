package wmyskxz.blog.module.vo;

/**
 * 用于与前台交互的分类数据模型
 *
 * @auth:wmyskxz
 * @date:2019/02/26 - 15:07
 */
public class CategoryVo {

    // UI显示的数据
    private String name;// 分类名称

    // UI隐藏的数据
    private Long categoryId;// 对应分类id(唯一)
    private Long userId;// 对应用户id

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
