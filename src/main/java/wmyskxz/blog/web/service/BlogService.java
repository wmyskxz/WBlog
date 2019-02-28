package wmyskxz.blog.web.service;

import wmyskxz.blog.module.entity.BlogContent;
import wmyskxz.blog.module.entity.BlogInfo;
import wmyskxz.blog.module.vo.BlogInfoVo;
import wmyskxz.blog.module.vo.BlogListVo;
import wmyskxz.blog.module.vo.BlogVo;

import java.util.List;

/**
 * 博客相关Service
 *
 * @auth:wmyskxz
 * @date:2019/02/26 - 13:24
 */
public interface BlogService {
    /**
     * 首页 - 获取最新的文章列表
     *
     * @return
     */
    List<BlogListVo> getNewestBlogs();

    /**
     * 首页 - 获取最热的文章列表
     *
     * @return
     */
    List<BlogListVo> getHotestBlogs();

    /**
     * 首页 - 获取最新推荐的文章列表
     *
     * @return
     */
    List<BlogListVo> getRecommendBlogs();

    /**
     * 个人主页 - 按照用户id来获取用户自定义的推荐文章列表
     *
     * @param userId
     * @return
     */
    List<BlogInfoVo> getRecommendBlogsByUserId(Long userId);

    /**
     * 个人主页(博客页) - 按照分类id(唯一)来获取文章列表
     *
     * @param categoryId
     * @return
     */
    List<BlogInfoVo> getBlogsByCategoryId(Long categoryId);

    /**
     * 个人主页(博文页) - 按照博文的id获取相应的博文信息
     *
     * @param blogId
     * @return
     */
    BlogVo getBlogByBlogId(Long blogId);

    /**
     * 个人主页(管理页) - 增加一篇博文
     *
     * @param blogInfo
     * @param blogContent
     * @param categoryId
     */
    void addBlog(BlogInfo blogInfo, BlogContent blogContent, Long categoryId);

    /**
     * 个人主页(管理页) - 通过文章id删除一篇博文
     *
     * @param blogId
     */
    void deleteBlogByBlogId(Long blogId);

    /**
     * 个人主页(管理页) - 修改一篇博文
     *
     * @param blogInfo
     * @param blogContent
     * @param categoryId
     */
    void updateBlog(BlogInfo blogInfo, BlogContent blogContent, Long categoryId);
}
