package wmyskxz.blog.web.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import wmyskxz.blog.module.entity.BlogContent;
import wmyskxz.blog.module.entity.BlogInfo;
import wmyskxz.blog.module.vo.base.PageResultVo;
import wmyskxz.blog.module.vo.base.ResponseVo;
import wmyskxz.blog.util.ResultUtil;
import wmyskxz.blog.web.service.BlogService;

import javax.annotation.Resource;

/**
 * 博客相关控制器
 *
 * @auth:wmyskxz
 * @date:2019/02/28 - 15:01
 */
@RestController // 返回JSON
@RequestMapping("apis/blog")
public class BlogController {

    /** 默认的分页大小 */
    private static final String PAGE_NUM = "0";
    private static final String PAGE_SIZE = "10";

    @Resource BlogService blogService;

    // 增加一篇博文
    @ApiOperation("增加一篇博文")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "title", value = "文章标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "summary", value = "文章简介", required = true, dataType = "String"),
            @ApiImplicitParam(name = "headPicture", value = "文章题图", required = false, dataType = "String"),
            @ApiImplicitParam(name = "contentMd", value = "文章内容md源码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "contentHTML", value = "文章内容html代码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "categoryId", value = "分类id", required = true, dataType = "Long")})
    @PostMapping("")
    public ResponseVo addBlog(BlogInfo blogInfo, BlogContent blogContent, Long categoryId) {
        blogService.addBlog(blogInfo, blogContent, categoryId);
        return ResultUtil.success("新增文章成功!");
    }

    // 删除一篇博文
    @ApiOperation("删除一篇博文")
    @ApiImplicitParam(name = "blogId", value = "文章id", required = true, dataType = "Long")
    @DeleteMapping("/{blogId}")
    public ResponseVo deleteBlog(@PathVariable Long blogId) {
        blogService.deleteBlogByBlogId(blogId);
        return ResultUtil.success("删除文章成功!");
    }

    // 修改一篇博文
    @ApiOperation("修改一篇博文")
    @ApiImplicitParams({@ApiImplicitParam(name = "title", value = "文章标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "summary", value = "文章简介", required = true, dataType = "String"),
            @ApiImplicitParam(name = "headPicture", value = "文章题图", required = false, dataType = "String"),
            @ApiImplicitParam(name = "contentMd", value = "文章内容md源码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "contentHTML", value = "文章内容html代码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "categoryId", value = "分类id", required = true, dataType = "Long")})
    @PutMapping("/{blogId}")
    public ResponseVo updateBlog(@PathVariable Long blogId, BlogInfo blogInfo, BlogContent blogContent,
                                 Long categoryId) {
        blogInfo.setId(blogId);
        blogService.updateBlog(blogInfo, blogContent, categoryId);
        return ResultUtil.success("修改文章成功!");
    }

    // 获取最新的文章列表
    @ApiOperation("获取最新的文章列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "开始页面", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, dataType = "int")})
    @GetMapping("/newest")
    public PageResultVo getNewestBlogs(@RequestParam(defaultValue = PAGE_NUM) int pageNum,
                                       @RequestParam(defaultValue = PAGE_SIZE) int pageSize) {
        return ResultUtil.table(blogService.getNewestBlogs(pageNum, pageSize), blogService.getBlogsNumber());
    }

    // 获取最热的文章列表
    @ApiOperation("获取最热的文章列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "开始页面", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, dataType = "int")})
    @GetMapping("/hotest")
    public PageResultVo getHotestBlogs(@RequestParam(defaultValue = PAGE_NUM) int pageNum,
                                       @RequestParam(defaultValue = PAGE_SIZE) int pageSize) {
        return ResultUtil.table(blogService.getHotestBlogs(pageNum, pageSize), blogService.getBlogsNumber());
    }

    // 获取推荐文章列表
    @ApiOperation("获取最新推荐的文章列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "开始页面", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, dataType = "int")})
    @GetMapping("/recommend")
    public PageResultVo getRecommendBlogs(@RequestParam(defaultValue = PAGE_NUM) int pageNum,
                                          @RequestParam(defaultValue = PAGE_SIZE) int pageSize) {
        return ResultUtil.table(blogService.getRecommendBlogs(pageNum, pageSize), blogService.getBlogsNumber());
    }

    // 获取某一篇博文详情
    @ApiOperation("获取某一篇博文详情")
    @GetMapping("/{blogId}")
    public ResponseVo getBlogById(@PathVariable("blogId") Long blogId) {
        return ResultUtil.success("获取成功!", blogService.getBlogByBlogId(blogId));
    }

    // 获取某一个用户的推荐文章列表
    @ApiOperation("获取某一个用户的推荐文章列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "开始页面", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, dataType = "int")})
    @GetMapping("/recommend/{userId}")
    public PageResultVo getRecommendBlogsByUserId(@PathVariable("userId") Long userId,
                                                  @RequestParam(defaultValue = PAGE_NUM) int pageNum,
                                                  @RequestParam(defaultValue = PAGE_SIZE) int pageSize) {
        return ResultUtil.table(blogService.getRecommendBlogsByUserId(userId, pageNum, pageSize),
                                blogService.getBlogsNumberByUserId(userId));
    }

    // 获取某一个用户的文章列表
    @ApiOperation("获取某一个用户的文章列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "开始页面", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, dataType = "int")})
    @GetMapping("/user/{userId}")
    public PageResultVo getBlogsByUserId(@PathVariable("userId") Long userId,
                                         @RequestParam(defaultValue = PAGE_NUM) int pageNum,
                                         @RequestParam(defaultValue = PAGE_SIZE) int pageSize) {
        return ResultUtil.table(blogService.getBlogsByUserId(userId, pageNum, pageSize),
                                blogService.getBlogsNumberByUserId(userId));
    }

    // 获取某一个用户某个分类下的文章列表
    @ApiOperation(value = "获取一个分类下的文章列表", notes = "分类id是唯一的")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "开始页面", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, dataType = "int")})
    @GetMapping("/category/{categoryId}")
    public PageResultVo getBlogsByUserIdAndCategoryId(@PathVariable("categoryId") Long categoryId,
                                                      @RequestParam(defaultValue = PAGE_NUM) int pageNum,
                                                      @RequestParam(defaultValue = PAGE_SIZE) int pageSize) {
        return ResultUtil.table(blogService.getBlogsByCategoryId(categoryId, pageNum, pageSize),
                                blogService.getBlogsNumberByCategoryId(categoryId));
    }

}
