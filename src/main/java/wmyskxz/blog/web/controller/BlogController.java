package wmyskxz.blog.web.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wmyskxz.blog.config.PageConfig;
import wmyskxz.blog.module.dao.es.EsBlogRepository;
import wmyskxz.blog.module.entity.BlogContent;
import wmyskxz.blog.module.entity.BlogInfo;
import wmyskxz.blog.module.vo.base.PageResultVo;
import wmyskxz.blog.module.vo.base.ResponseVo;
import wmyskxz.blog.util.ConstCode;
import wmyskxz.blog.util.ResultUtil;
import wmyskxz.blog.web.service.BlogService;
import wmyskxz.blog.web.service.SearchService;

/**
 * 博客相关控制器
 *
 * @auth:wmyskxz
 * @date:2019/02/28 - 15:01
 */
@RestController // 返回JSON
@RequestMapping("/apis/blog")
public class BlogController {

    @Autowired BlogService blogService;
    @Autowired EsBlogRepository esBlogRepository;
    @Autowired SearchService searchService;

    // 增加一篇博文
    @ApiOperation("增加一篇博文")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "title", value = "文章标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "summary", value = "文章简介", required = true, dataType = "String"),
            @ApiImplicitParam(name = "headPicture", value = "文章题图", required = false, dataType = "String"),
            @ApiImplicitParam(name = "contentMd", value = "文章内容md源码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "contentHtml", value = "文章内容html代码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "categoryId", value = "分类id", required = true, dataType = "Long")})
    @PostMapping("")
    public ResponseVo addBlog(BlogInfo blogInfo, BlogContent blogContent, Long categoryId) {
        blogService.add(blogInfo, blogContent, categoryId);
        return ResultUtil.success("新增文章成功!");
    }

    // 删除一篇博文
    @ApiOperation("删除一篇博文")
    @ApiImplicitParam(name = "blogId", value = "文章id", required = true, dataType = "Long")
    @DeleteMapping("/{blogId}")
    public ResponseVo deleteBlog(@PathVariable Long blogId) {
        blogService.deleteById(blogId);
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
        blogService.update(blogInfo, blogContent, categoryId);
        return ResultUtil.success("修改文章成功!");
    }

    // 未登录 - 获取最新的文章列表
    @ApiOperation("未登录 - 获取最新的文章列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "开始页面", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, dataType = "int")})
    @GetMapping("/newest")
    public PageResultVo getNewestBlogs(@RequestParam(defaultValue = PageConfig.PAGE_NUM) int pageNum,
                                       @RequestParam(defaultValue = PageConfig.PAGE_SIZE) int pageSize) {
        return ResultUtil.table(blogService.listNewestBlogs(null, pageNum, pageSize), blogService.countAll());
    }

    // 登录 - 获取最新的文章列表
    @ApiOperation("登录 - 获取最新的文章列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "开始页面", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, dataType = "int")})
    @GetMapping("/newest/{userId}")
    public PageResultVo getNewestBlogs(@PathVariable Long userId,
                                       @RequestParam(defaultValue = PageConfig.PAGE_NUM) int pageNum,
                                       @RequestParam(defaultValue = PageConfig.PAGE_SIZE) int pageSize) {
        return ResultUtil.table(blogService.listNewestBlogs(userId, pageNum, pageSize), blogService.countAll());
    }

    // 未登录 - 获取最热的文章列表
    @ApiOperation("未登录 - 获取最热的文章列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "开始页面", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, dataType = "int")})
    @GetMapping("/hotest")
    public PageResultVo getHotestBlogs(@RequestParam(defaultValue = PageConfig.PAGE_NUM) int pageNum,
                                       @RequestParam(defaultValue = PageConfig.PAGE_SIZE) int pageSize) {
        return ResultUtil.table(blogService.listHotestBlogs(null, pageNum, pageSize), blogService.countAll());
    }

    // 登录 - 获取最热的文章列表
    @ApiOperation("登录 - 获取最热的文章列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "开始页面", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, dataType = "int")})
    @GetMapping("/hotest/{userId}")
    public PageResultVo getHotestBlogs(@PathVariable Long userId,
                                       @RequestParam(defaultValue = PageConfig.PAGE_NUM) int pageNum,
                                       @RequestParam(defaultValue = PageConfig.PAGE_SIZE) int pageSize) {
        return ResultUtil.table(blogService.listHotestBlogs(userId, pageNum, pageSize), blogService.countAll());
    }

    // 未登录 - 获取推荐文章列表
    @ApiOperation("未登录 - 获取最新推荐的文章列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "开始页面", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, dataType = "int")})
    @GetMapping("/recommend")
    public PageResultVo getRecommendBlogs(@RequestParam(defaultValue = PageConfig.PAGE_NUM) int pageNum,
                                          @RequestParam(defaultValue = PageConfig.PAGE_SIZE) int pageSize) {
        return ResultUtil
                .table(blogService.listRecommendBlogs(null, pageNum, pageSize), blogService.countAllRecommend());
    }

    // 登录 - 获取推荐文章列表
    @ApiOperation("登录 - 获取最新推荐的文章列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "开始页面", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, dataType = "int")})
    @GetMapping("/recommend/{userId}")
    public PageResultVo getRecommendBlogs(@PathVariable Long userId,
                                          @RequestParam(defaultValue = PageConfig.PAGE_NUM) int pageNum,
                                          @RequestParam(defaultValue = PageConfig.PAGE_SIZE) int pageSize) {
        return ResultUtil
                .table(blogService.listRecommendBlogs(userId, pageNum, pageSize), blogService.countAllRecommend());
    }

    // 获取某一篇博文详情
    @ApiOperation("获取某一篇博文详情")
    @GetMapping("/{blogId}")
    public ResponseVo getBlogById(@PathVariable("blogId") Long blogId) {
        return ResultUtil.success("获取成功!", blogService.findById(blogId));
    }

    // 获取某一个用户的推荐文章列表
    @ApiOperation("获取某一个用户的推荐文章列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "开始页面", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, dataType = "int")})
    @GetMapping("/recommend/user/{userId}")
    public PageResultVo getRecommendBlogsByUserId(@PathVariable("userId") Long userId,
                                                  @RequestParam(defaultValue = PageConfig.PAGE_NUM) int pageNum,
                                                  @RequestParam(defaultValue = PageConfig.PAGE_SIZE) int pageSize) {
        return ResultUtil.table(blogService.listRecommendBlogsByUserId(userId, pageNum, pageSize),
                                blogService.countByUserId(userId));
    }

    // 获取某一个用户的文章列表
    @ApiOperation("获取某一个用户的文章列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "开始页面", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, dataType = "int")})
    @GetMapping("/user/{userId}")
    public PageResultVo getBlogsByUserId(@PathVariable("userId") Long userId,
                                         @RequestParam(defaultValue = PageConfig.PAGE_NUM) int pageNum,
                                         @RequestParam(defaultValue = PageConfig.PAGE_SIZE) int pageSize) {
        return ResultUtil.table(blogService.listByUserId(userId, pageNum, pageSize), blogService.countByUserId(userId));
    }

    // 获取某一个用户某个分类下的文章列表
    @ApiOperation(value = "获取一个分类下的文章列表", notes = "分类id是唯一的")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "开始页面", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, dataType = "int")})
    @GetMapping("/category/{categoryId}")
    public PageResultVo getBlogsByUserIdAndCategoryId(@PathVariable("categoryId") Long categoryId,
                                                      @RequestParam(defaultValue = PageConfig.PAGE_NUM) int pageNum,
                                                      @RequestParam(defaultValue = PageConfig.PAGE_SIZE) int pageSize) {
        return ResultUtil.table(blogService.listByCategoryId(categoryId, pageNum, pageSize),
                                blogService.countByCategoryId(categoryId));
    }

    // 获取某一篇博文数据(编辑时)
    @ApiOperation("获取某一篇博文数据(编辑时)")
    @GetMapping("/edit/{blogId}")
    public ResponseVo getBlogEditVo(@PathVariable Long blogId) {
        return ResultUtil.success("获取成功!", blogService.findBlogEditVoById(blogId));
    }

    // 获取某一个用户的最热文章列表
    @ApiOperation("获取某一个用户的最热文章列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "开始页面", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, dataType = "int")})
    @GetMapping("/blog/hotest/{userId}")
    public PageResultVo getHotestBlogsByUserId(@PathVariable("userId") Long userId,
                                               @RequestParam(defaultValue = PageConfig.PAGE_NUM) int pageNum,
                                               @RequestParam(defaultValue = PageConfig.PAGE_SIZE) int pageSize) {
        return ResultUtil
                .table(blogService.listHotestByUserId(userId, pageNum, pageSize), blogService.countByUserId(userId));
    }

    @GetMapping("es")
    public ResponseVo getAllEsBlogs() {
        return ResultUtil.success("成功!", esBlogRepository.findAll());
    }

    @GetMapping("/search/")
    public PageResultVo search(String keyword, @RequestParam(defaultValue = PageConfig.PAGE_NUM) int pageNum,
                               @RequestParam(defaultValue = PageConfig.PAGE_SIZE) int pageSize) {
        return ResultUtil.table(searchService.search(keyword, pageNum, pageSize), ConstCode.DEFAULT_NO_PAGING);
    }
}
