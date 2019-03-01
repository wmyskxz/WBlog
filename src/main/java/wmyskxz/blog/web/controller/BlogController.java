package wmyskxz.blog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wmyskxz.blog.module.vo.base.ResponseVo;
import wmyskxz.blog.web.service.BlogService;

import javax.annotation.Resource;

/**
 * 博客相关控制器
 *
 * @auth:wmyskxz
 * @date:2019/02/28 - 15:01
 */
@Controller
@RequestMapping("apis/blog")
public class BlogController {

    @Resource
    BlogService blogService;

    // 增加一篇博文
    @PostMapping("")// api/blog/
    public ResponseVo addBlog() {
        return null;
    }

    // 删除一篇博文
    @DeleteMapping("/{blogId}")// api/blog/
    public ResponseVo deleteBlog(@PathVariable Long blogId) {
        return null;
    }

    // 修改一篇博文
    @PutMapping("/{blogId}")// api/blog/
    public ResponseVo updateBlog(@PathVariable Long blogId) {
        return null;
    }

    // 获取最新的文章列表
    @GetMapping("/newest")// api/blog/newest
    public ResponseVo getNewestBlogs() {
        return null;
    }

    // 获取最热的文章列表
    @GetMapping("/hotest")// api/blog/hotest
    public ResponseVo getHotestBlogs() {
        return null;
    }

    // 获取推荐文章列表
    @GetMapping("/recommend")// api/blog/recommend
    public ResponseVo getRecommendBlogs() {
        return null;
    }

    // 获取某一篇博文详情
    @GetMapping("/{blogId}")// api/blog/{blogId}
    public ResponseVo getBlogById(@PathVariable("blogId") Long blogId) {
        return null;
    }

    // 获取某一个用户的推荐文章列表
    @GetMapping("/recommend/{userId}")// api/blog/recommend/{userId}
    public ResponseVo getRecommendBlogsByUserId(@PathVariable("userId") Long userId) {
        return null;
    }

    // 获取某一个用户的文章列表
    @GetMapping("/user/{userId}")// api/blog/user/{userId}
    public ResponseVo getBlogsByUserId(@PathVariable("userId") Long userId) {
        return null;
    }

    // 获取某一个用户某个分类下的文章列表
    @GetMapping("/category/{categoryId}")// api/blog/user/{userId}/{categoryId}
    public ResponseVo getBlogsByUserIdAndCategoryId(@PathVariable("categoryId") Long categoryId) {
        return null;
    }

}
