package wmyskxz.blog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wmyskxz.blog.module.vo.base.ResponseVo;

/**
 * 分类控制器
 *
 * @auth:wmyskxz
 * @date:2019/02/28 - 18:52
 */
@Controller
@RequestMapping("/apis/category")
public class CategoryController {

    // 增加一个分类
    @PostMapping("")
    public ResponseVo addCategory() {
        return null;
    }

    // 删除一个分类
    @DeleteMapping("{categoryId}")
    public ResponseVo deleteCategory(@PathVariable Long categoryId) {
        return null;
    }

    // 修改一个分类
    @PutMapping()
    public ResponseVo updateCategory() {
        return null;
    }

    // 查询一个用户的所有分类
    @GetMapping("/{userId}")
    public ResponseVo listAll(@PathVariable Long userId) {
        return null;
    }
}
