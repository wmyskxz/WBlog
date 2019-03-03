package wmyskxz.blog.web.service;

import wmyskxz.blog.module.vo.CategoryVo;

import java.util.List;

/**
 * 分类Service接口
 *
 * @auth:wmyskxz
 * @date:2019/02/26 - 15:06
 */
public interface CategoryService {
    /**
     * 增加分类(不允许同一个用户创建相同的分类名称)
     *
     * @param name
     * @param userId
     */
    void add(String name, Long userId);

    /**
     * 通过categoryId删除对应的分类(如果该分类下还有文章则默认不允许删除)
     *
     * @param categoryId
     */
    void deleteById(Long categoryId);

    /**
     * 修改分类信息
     *
     * @param categoryVo
     */
    void update(CategoryVo categoryVo);

    /**
     * 通过用户id获取该用户下的所有分类信息
     *
     * @param userId
     * @return
     */
    List<CategoryVo> listByUserId(Long userId);

    /**
     * 查询一个用户下分类的个数
     *
     * @param userId
     * @return
     */
    Long countByUserId(Long userId);
}
