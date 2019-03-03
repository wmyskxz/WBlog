package wmyskxz.blog.web.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wmyskxz.blog.module.vo.CategoryVo;
import wmyskxz.blog.module.vo.base.PageResultVo;
import wmyskxz.blog.module.vo.base.ResponseVo;
import wmyskxz.blog.util.ResultUtil;
import wmyskxz.blog.web.service.CategoryService;

/**
 * 分类控制器
 *
 * @auth:wmyskxz
 * @date:2019/02/28 - 18:52
 */
@RestController// 返回JSON
@RequestMapping("/apis/category")
public class CategoryController {

    @Autowired CategoryService categoryService;

    // 增加一个分类
    @ApiOperation("增加一个分类")
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "分类名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "Long"),})
    @PostMapping("")
    public ResponseVo addCategory(CategoryVo categoryVo) {
        categoryService.update(categoryVo);
        return ResultUtil.success("添加成功!");
    }

    // 删除一个分类
    @ApiOperation("删除一个分类")
    @DeleteMapping("{categoryId}")
    public ResponseVo deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteById(categoryId);
        return ResultUtil.success("删除成功!");
    }

    // 修改一个分类
    @ApiOperation("修改一个分类")
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "分类名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "categoryId", value = "分类id", required = true, dataType = "Long"),})
    @PutMapping()
    public ResponseVo updateCategory(CategoryVo categoryVo) {
        categoryService.update(categoryVo);
        return ResultUtil.success("修改成功!");
    }

    // 查询一个用户的所有分类
    @ApiOperation("查询一个用户的所有分类")
    @GetMapping("/{userId}")
    public PageResultVo listAll(@PathVariable Long userId) {
        return ResultUtil.table(categoryService.listByUserId(userId), categoryService.countByUserId(userId));
    }
}
