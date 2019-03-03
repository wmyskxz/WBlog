package wmyskxz.blog.web.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wmyskxz.blog.module.dao.BlogCategoryMapper;
import wmyskxz.blog.module.dao.BlogContentMapper;
import wmyskxz.blog.module.dao.BlogInfoMapper;
import wmyskxz.blog.module.dao.UserMapper;
import wmyskxz.blog.module.entity.*;
import wmyskxz.blog.module.vo.BlogInfoVo;
import wmyskxz.blog.module.vo.BlogListVo;
import wmyskxz.blog.module.vo.BlogVo;
import wmyskxz.blog.web.service.BlogService;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * BlogService实现类
 *
 * @auth:wmyskxz
 * @date:2019/02/26 - 14:55
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Resource BlogInfoMapper blogInfoMapper;
    @Resource UserMapper userMapper;
    @Resource BlogCategoryMapper blogCategoryMapper;
    @Resource BlogContentMapper blogContentMapper;

    @Override
    @Transactional// 开启事务
    public Long countAll() {
        BlogInfoExample blogInfoExample = new BlogInfoExample();
        blogInfoExample.or();// 无条件查询即查询全部
        Long count = blogInfoMapper.countByExample(blogInfoExample);
        return count;
    }

    @Override
    @Transactional// 开启事务
    public Long countByUserId(Long userId) {
        BlogInfoExample blogInfoExample = new BlogInfoExample();
        blogInfoExample.or().andUserIdEqualTo(userId);
        Long count = blogInfoMapper.countByExample(blogInfoExample);
        return count;
    }

    @Override
    @Transactional// 开启事务
    public Long countByCategoryId(Long categoryId) {
        BlogCategoryExample blogCategoryExample = new BlogCategoryExample();
        blogCategoryExample.or().andCategoryIdEqualTo(categoryId);
        Long count = blogCategoryMapper.countByExample(blogCategoryExample);
        return count;
    }

    @Override
    @Transactional// 开启事务
    public List<BlogListVo> listNewestBlogs(int pageNum, int pageSize) {

        List<BlogListVo> resultList = new LinkedList<>();

        BlogInfoExample blogInfoExample = new BlogInfoExample();
        blogInfoExample.setOrderByClause("create_time DESC");// 设置按照创建时间降序排列
        PageHelper.startPage(pageNum, pageSize);// 只对下一次的查询生效
        List<BlogInfo> blogInfos = blogInfoMapper.selectByExample(blogInfoExample);

        // 拼接数据
        resultList = joinBlogListVo(blogInfos);

        return resultList;
    }

    @Override
    @Transactional// 开启事务
    public List<BlogListVo> listHotestBlogs(int pageNum, int pageSize) {

        List<BlogListVo> resultList = new LinkedList<>();

        BlogInfoExample blogInfoExample = new BlogInfoExample();
        blogInfoExample.setOrderByClause("vote_size DESC");// 按照点赞数降序排列
        PageHelper.startPage(pageNum, pageSize);// 只对下一次的查询生效
        List<BlogInfo> blogInfos = blogInfoMapper.selectByExample(blogInfoExample);

        // 拼接数据
        resultList = joinBlogListVo(blogInfos);

        return resultList;
    }

    @Override
    @Transactional// 开启事务
    public List<BlogListVo> listRecommendBlogs(int pageNum, int pageSize) {

        List<BlogListVo> resultList = new LinkedList<>();

        BlogInfoExample blogInfoExample = new BlogInfoExample();
        blogInfoExample.or().andIsRecommendEqualTo(true);
        PageHelper.startPage(pageNum, pageSize);// 只对下一次的查询生效
        List<BlogInfo> blogInfos = blogInfoMapper.selectByExample(blogInfoExample);

        // 拼接数据
        resultList = joinBlogListVo(blogInfos);

        return resultList;
    }

    @Override
    @Transactional// 开启事务
    public List<BlogInfoVo> listRecommendBlogsByUserId(Long userId, int pageNum, int pageSize) {

        List<BlogInfoVo> resultList = new LinkedList<>();

        BlogInfoExample blogInfoExample = new BlogInfoExample();
        blogInfoExample.or().andUserIdEqualTo(userId).andIsRecommendEqualTo(true);
        PageHelper.startPage(pageNum, pageSize);// 只对下一次的查询生效
        List<BlogInfo> blogInfos = blogInfoMapper.selectByExample(blogInfoExample);

        // 拼接数据
        resultList = joinBlogInfoVo(blogInfos);

        return resultList;
    }

    @Override
    @Transactional// 开启事务
    public List<BlogInfoVo> listByUserId(Long userId, int pageNum, int pageSize) {
        List<BlogInfoVo> resultList = new LinkedList<>();

        BlogInfoExample blogInfoExample = new BlogInfoExample();
        blogInfoExample.or().andUserIdEqualTo(userId);
        PageHelper.startPage(pageNum, pageSize);// 只对下一次的查询生效
        List<BlogInfo> blogInfos = blogInfoMapper.selectByExample(blogInfoExample);

        // 拼接数据
        resultList = joinBlogInfoVo(blogInfos);

        return resultList;
    }

    @Override
    @Transactional// 开启事务
    public List<BlogInfoVo> listByCategoryId(Long categoryId, int pageNum, int pageSize) {

        List<BlogInfoVo> resultList = new LinkedList<>();

        BlogCategoryExample blogCategoryExample = new BlogCategoryExample();
        blogCategoryExample.or().andCategoryIdEqualTo(categoryId);
        PageHelper.startPage(pageNum, pageSize);// 只对下一次的查询生效
        List<BlogCategory> blogCategoryList = blogCategoryMapper.selectByExample(blogCategoryExample);

        List<BlogInfo> blogInfos = new LinkedList<>();
        BlogInfo blogInfo;
        for (BlogCategory blogCategory : blogCategoryList) {
            blogInfo = blogInfoMapper.selectByPrimaryKey(blogCategory.getBlogId());
            blogInfos.add(blogInfo);
        }   // end for: blogInfos保存了该分类下的相关博文

        // 拼接数据
        resultList = joinBlogInfoVo(blogInfos);

        return resultList;
    }

    @Override
    @Transactional// 开启事务
    public BlogVo findById(Long blogId) {

        BlogVo resultObject = new BlogVo();

        BlogInfo blogInfo = blogInfoMapper.selectByPrimaryKey(blogId);
        // 将blogInfo中同BlogVo对象中相同的字段赋值给BlogVo
        BeanUtils.copyProperties(blogInfo, resultObject);
        // 添加相关的用户信息
        User user = userMapper.selectByPrimaryKey(blogInfo.getUserId());
        BeanUtils.copyProperties(user, resultObject);

        return resultObject;
    }

    @Override
    @Transactional// 开启事务
    public void add(BlogInfo blogInfo, BlogContent blogContent, Long categoryId) {

        // 1.先把BlogInfo基础信息插入到表中
        Long blogId = Long.valueOf(blogInfoMapper.insertSelective(blogInfo));

        // 2.给BlogContent设置id插入到表中
        blogContent.setBlogId(blogId);
        blogContentMapper.insertSelective(blogContent);

        // 3.拼接相关数据插入到BlogCategory表中
        BlogCategory blogCategory = new BlogCategory();
        blogCategory.setBlogId(blogId);
        blogCategory.setCategoryId(categoryId);
        blogCategoryMapper.insertSelective(blogCategory);
    }

    @Override
    @Transactional// 开启事务
    public void deleteById(Long blogId) {

        // 1.先删除BlogInfo基础表中的相关内容
        blogInfoMapper.deleteByPrimaryKey(blogId);

        // 2.再删除BlogContent内容表中的内容
        BlogContentExample blogContentExample = new BlogContentExample();
        blogContentExample.or().andBlogIdEqualTo(blogId);
        blogContentMapper.deleteByExample(blogContentExample);

        // 3.最后删除BlogCategory分类表中的相关内容
        BlogCategoryExample blogCategoryExample = new BlogCategoryExample();
        blogCategoryExample.or().andBlogIdEqualTo(blogId);
        blogCategoryMapper.deleteByExample(blogCategoryExample);

    }

    @Override
    @Transactional// 开启事务
    public void update(BlogInfo blogInfo, BlogContent blogContent, Long categoryId) {

    }

    /**
     * 按照传入的BlogInfo集合拼接需要的BlogListVo数据集合
     *
     * @param blogInfos
     * @return
     */
    private List<BlogListVo> joinBlogListVo(List<BlogInfo> blogInfos) {
        List<BlogListVo> resultList = new LinkedList<>();

        // 拼接数据
        BlogListVo blogListVo;
        for (BlogInfo blogInfo : blogInfos) {
            blogListVo = new BlogListVo();

            // 将blogInfo中同blogListVo相同字段赋值给blogListVo
            BeanUtils.copyProperties(blogInfo, blogListVo);
            // 将blogInfo所属用户的信息赋值给blogListVo
            User user = userMapper.selectByPrimaryKey(blogInfo.getUserId());
            BeanUtils.copyProperties(user, blogListVo);

            resultList.add(blogListVo);
        }   // end for

        return resultList;
    }

    /**
     * 按照传入的BlogInfo集合拼接需要的BlogInfoVo数据集合
     *
     * @param blogInfos
     * @return
     */
    private List<BlogInfoVo> joinBlogInfoVo(List<BlogInfo> blogInfos) {

        List<BlogInfoVo> resultList = new LinkedList<>();

        // 拼接数据
        BlogInfoVo blogInfoVo;
        for (BlogInfo blogInfo : blogInfos) {
            blogInfoVo = new BlogInfoVo();
            // 将blogInfo中同blogInfoVo相同字段赋值给blogInfoVo
            BeanUtils.copyProperties(blogInfo, blogInfoVo);

            resultList.add(blogInfoVo);
        }   // end for

        return resultList;
    }
}
