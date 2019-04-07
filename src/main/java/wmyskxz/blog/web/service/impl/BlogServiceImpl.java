package wmyskxz.blog.web.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wmyskxz.blog.module.dao.*;
import wmyskxz.blog.module.dao.es.EsBlogRepository;
import wmyskxz.blog.module.entity.*;
import wmyskxz.blog.module.vo.BlogEditVo;
import wmyskxz.blog.module.vo.BlogInfoVo;
import wmyskxz.blog.module.vo.BlogListVo;
import wmyskxz.blog.module.vo.BlogVo;
import wmyskxz.blog.module.vo.es.EsBlog;
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
    @Resource VoteMapper voteMapper;
    @Autowired EsBlogRepository esBlogRepository;

    @Override
    @Transactional// 开启事务
    public Boolean checkTitle(String title) {

        BlogInfoExample blogInfoExample = new BlogInfoExample();
        blogInfoExample.or().andTitleEqualTo(title);
        if (blogInfoMapper.selectByExample(blogInfoExample).isEmpty()) {
            // 为空则表明没有用当前title的文章
            return true;
        } else return false;
    }

    @Override
    @Transactional// 开启事务
    public BlogEditVo findBlogEditVoById(Long blogId) {

        BlogEditVo resultObject = new BlogEditVo();

        // 查询Blog基础信息
        BlogInfo blogInfo = blogInfoMapper.selectByPrimaryKey(blogId);
        // 把blogInfo中同resultObject对象中的相同字段赋值给后者
        // 还剩下contentMd/contentHtml/categoryId没有赋值了
        BeanUtils.copyProperties(blogInfo, resultObject);
        resultObject.setRecommend(blogInfo.getIsRecommend());

        // 查询Blog内容信息
        BlogContentExample blogContentExample = new BlogContentExample();
        blogContentExample.or().andBlogIdEqualTo(blogId);
        BlogContent blogContent = blogContentMapper.selectByExample(blogContentExample).get(0);
        // 注入contentMd/contentHtml字段
        BeanUtils.copyProperties(blogContent, resultObject);

        BlogCategoryExample blogCategoryExample = new BlogCategoryExample();
        blogCategoryExample.or().andBlogIdEqualTo(blogId);
        BlogCategory blogCategory = blogCategoryMapper.selectByExample(blogCategoryExample).get(0);
        // 注入categoryId字段
        resultObject.setCategoryId(blogCategory.getCategoryId());

        return resultObject;
    }

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
    public Long countAllRecommend() {
        BlogInfoExample blogInfoExample = new BlogInfoExample();
        blogInfoExample.or().andIsRecommendEqualTo(true);
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
    public List<BlogListVo> listNewestBlogs(Long userId, int pageNum, int pageSize) {

        List<BlogListVo> resultList = new LinkedList<>();

        BlogInfoExample blogInfoExample = new BlogInfoExample();
        blogInfoExample.setOrderByClause("create_time DESC");// 设置按照创建时间降序排列
        PageHelper.startPage(pageNum, pageSize);// 只对下一次的查询生效
        List<BlogInfo> blogInfos = blogInfoMapper.selectByExample(blogInfoExample);

        // 拼接数据
        resultList = joinBlogListVo(userId, blogInfos);

        return resultList;
    }

    @Override
    @Transactional// 开启事务
    public List<BlogListVo> listHotestBlogs(Long userId, int pageNum, int pageSize) {

        List<BlogListVo> resultList;

        BlogInfoExample blogInfoExample = new BlogInfoExample();
        blogInfoExample.setOrderByClause("vote_size DESC");// 按照点赞数降序排列
        PageHelper.startPage(pageNum, pageSize);// 只对下一次的查询生效
        List<BlogInfo> blogInfos = blogInfoMapper.selectByExample(blogInfoExample);

        // 拼接数据
        resultList = joinBlogListVo(userId, blogInfos);

        return resultList;
    }

    @Override
    @Transactional// 开启事务
    public List<BlogListVo> listRecommendBlogs(Long userId, int pageNum, int pageSize) {

        List<BlogListVo> resultList = new LinkedList<>();

        BlogInfoExample blogInfoExample = new BlogInfoExample();
        blogInfoExample.or().andIsRecommendEqualTo(true);
        PageHelper.startPage(pageNum, pageSize);// 只对下一次的查询生效
        List<BlogInfo> blogInfos = blogInfoMapper.selectByExample(blogInfoExample);

        // 拼接数据
        resultList = joinBlogListVo(userId, blogInfos);

        return resultList;
    }

    @Override
    @Transactional// 开启事务
    public List<BlogInfoVo> listRecommendBlogsByUserId(Long userId, int pageNum, int pageSize) {

        List<BlogInfoVo> resultList;

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
        // 添加相关的用户信息
        User user = userMapper.selectByPrimaryKey(blogInfo.getUserId());
        BeanUtils.copyProperties(user, resultObject);
        // 将blogInfo中同BlogVo对象中相同的字段赋值给BlogVo
        BeanUtils.copyProperties(blogInfo, resultObject);
        resultObject.setBlogId(blogInfo.getId());
        // 添加内容
        BlogContentExample blogContentExample = new BlogContentExample();
        blogContentExample.or().andBlogIdEqualTo(blogId);
        BlogContent blogContent = blogContentMapper.selectByExample(blogContentExample).get(0);
        resultObject.setContentHtml(blogContent.getContentHtml());

        // 阅读数加1
        blogInfo.setReadSize(blogInfo.getReadSize() + 1);
        blogInfoMapper.updateByPrimaryKeySelective(blogInfo);
        EsBlog esBlog = esBlogRepository.findById(blogId).get();
        esBlog.setReadSize(blogInfo.getReadSize());
        esBlogRepository.save(esBlog);

        return resultObject;
    }

    @Override
    @Transactional// 开启事务
    public void add(BlogInfo blogInfo, BlogContent blogContent, Long categoryId) {

        // 1.先把BlogInfo基础信息插入到表中
        blogInfoMapper.insertSelective(blogInfo);

        // 2.给BlogContent设置id插入到表中
        blogContent.setBlogId(blogInfo.getId());
        blogContentMapper.insertSelective(blogContent);

        // 3.拼接相关数据插入到BlogCategory表中
        BlogCategory blogCategory = new BlogCategory();
        blogCategory.setBlogId(blogInfo.getId());
        blogCategory.setCategoryId(categoryId);
        blogCategoryMapper.insertSelective(blogCategory);

        // 4.往Es中塞数据
        EsBlog esBlog = new EsBlog();
        BeanUtils.copyProperties(blogInfo, esBlog);
        esBlog.setBlogId(blogInfo.getId());
        esBlog.setContent(blogContent.getContentHtml());
        esBlogRepository.save(esBlog);
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

        // 4.删除es中的数据
        esBlogRepository.deleteById(blogId);
    }

    @Override
    @Transactional// 开启事务
    public void update(BlogInfo blogInfo, BlogContent blogContent, Long categoryId) {
        // 1.更新BlogInfo信息
        blogInfoMapper.updateByPrimaryKeySelective(blogInfo);

        // 2.更新BlogContent信息
        BlogContentExample blogContentExample = new BlogContentExample();
        blogContentExample.or().andBlogIdEqualTo(blogInfo.getId());
        blogContent.setId(blogContentMapper.selectByExample(blogContentExample).get(0).getId());
        blogContentMapper.updateByPrimaryKeySelective(blogContent);

        // 3.更新分类信息
        BlogCategoryExample blogCategoryExample = new BlogCategoryExample();
        blogCategoryExample.or().andBlogIdEqualTo(blogInfo.getId());
        BlogCategory blogCategory = blogCategoryMapper.selectByExample(blogCategoryExample).get(0);
        blogCategory.setCategoryId(categoryId);
        blogCategoryMapper.updateByPrimaryKeySelective(blogCategory);

        // 4.更新es中的数据
        EsBlog esBlog = new EsBlog();
        BeanUtils.copyProperties(blogInfo, esBlog);
        esBlog.setBlogId(blogInfo.getId());
        esBlog.setContent(blogContent.getContentHtml());
        esBlogRepository.save(esBlog);
    }

    /**
     * 按照传入的BlogInfo集合拼接需要的BlogListVo数据集合
     *
     * @param userId
     * @param blogInfos
     * @return
     */
    private List<BlogListVo> joinBlogListVo(Long userId, List<BlogInfo> blogInfos) {
        List<BlogListVo> resultList = new LinkedList<>();

        // 拼接数据
        BlogListVo blogListVo;
        for (BlogInfo blogInfo : blogInfos) {
            blogListVo = new BlogListVo();
            blogListVo.setBlogId(blogInfo.getId());
            // 将blogInfo所属用户的信息赋值给blogListVo
            User user = userMapper.selectByPrimaryKey(blogInfo.getUserId());
            BeanUtils.copyProperties(user, blogListVo);
            // 将blogInfo中同blogListVo相同字段赋值给blogListVo
            BeanUtils.copyProperties(blogInfo, blogListVo);
            // 按照是否有userId来设置isVote属性,如果为null则统一设置为false
            if (null != userId) {
                VoteExample voteExample = new VoteExample();
                voteExample.or().andUserIdEqualTo(userId).andBlogIdEqualTo(blogInfo.getId());
                if (voteMapper.selectByExample(voteExample).isEmpty()) {
                    // 如果是空(没有点赞)
                    blogListVo.setVote(false);
                } else blogListVo.setVote(true);
            } else blogListVo.setVote(false);

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
            blogInfoVo.setBlogId(blogInfo.getId());
            resultList.add(blogInfoVo);
        }   // end for

        return resultList;
    }

    @Override
    @Transactional// 开启事务
    public List<BlogInfoVo> listHotestByUserId(Long userId, int pageNum, int pageSize) {

        List<BlogInfoVo> resultList;

        BlogInfoExample blogInfoExample = new BlogInfoExample();
        blogInfoExample.or().andUserIdEqualTo(userId);
        blogInfoExample.setOrderByClause("vote_size DESC");// 按照点赞数降序排列
        PageHelper.startPage(pageNum, pageSize);// 只对下一次的查询生效
        List<BlogInfo> blogInfos = blogInfoMapper.selectByExample(blogInfoExample);

        // 拼接数据
        resultList = joinBlogInfoVo(blogInfos);

        return resultList;
    }
}
