package wmyskxz.blog.web.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import wmyskxz.blog.module.dao.BlogCategoryMapper;
import wmyskxz.blog.module.dao.CategoryMapper;
import wmyskxz.blog.module.entity.BlogCategoryExample;
import wmyskxz.blog.module.entity.Category;
import wmyskxz.blog.module.entity.CategoryExample;
import wmyskxz.blog.module.vo.CategoryVo;
import wmyskxz.blog.web.service.CategoryService;

import java.util.LinkedList;
import java.util.List;

/**
 * CategoryService实现类
 *
 * @auth:wmyskxz
 * @date:2019/02/27 - 13:58
 */
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    BlogCategoryMapper blogCategoryMapper;

    @Override
    @Transactional// 开启事务
    public void addCategory(String name, Long userId) {
        Category category = new Category();
        category.setName(name);
        category.setUserId(userId);
        categoryMapper.insertSelective(category);
    }

    @Override
    @Transactional// 开启事务
    public void deleteCategoryByCategoryId(Long categoryId) {
        // 默认情况下该分类下还有文章是不允许用户删除的
        BlogCategoryExample blogCategoryExample = new BlogCategoryExample();
        blogCategoryExample.or().andCategoryIdEqualTo(categoryId);
        if (blogCategoryMapper.selectByExample(blogCategoryExample).isEmpty()) {
            categoryMapper.deleteByPrimaryKey(categoryId);
        }   // end if
    }

    @Override
    @Transactional// 开启事务
    public void updateCategory(CategoryVo categoryVo) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryVo, category);
        categoryMapper.insertSelective(category);
    }

    @Override
    @Transactional// 开启事务
    public List<CategoryVo> getCategoriesByUserId(Long userId) {
        List<CategoryVo> resultList = new LinkedList<>();

        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andUserIdEqualTo(userId);
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);

        // 拼接数据
        CategoryVo categoryVo = new CategoryVo();
        for (Category category : categoryList) {
            // 把Category类同CategoryVo的相同字段赋值给CategoryVo类
            BeanUtils.copyProperties(category, categoryVo);
            resultList.add(categoryVo);
        }   // end for

        return resultList;
    }
}
