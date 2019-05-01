package wmyskxz.blog.web.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wmyskxz.blog.config.PageConfig;
import wmyskxz.blog.module.vo.base.PageResultVo;
import wmyskxz.blog.module.vo.base.ResponseVo;
import wmyskxz.blog.util.ResultUtil;
import wmyskxz.blog.web.service.UserService;

import java.io.*;

/**
 * 用户相关控制器
 *
 * @auth:wmyskxz
 * @date:2019/03/01 - 22:48
 */
@RestController
@RequestMapping("/apis/user")
public class UserController {

    @Autowired UserService userService;

    // 增加一个用户(后台)
    @ApiOperation("增加一个用户(后台)")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "用户账号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "email", value = "用户邮箱", required = true, dataType = "String")})
    @PostMapping("")
    public ResponseVo add(String username, String password, String email) {
        userService.register(username, password, email);
        return ResultUtil.success("添加成功!");
    }

    // 删除一个用户
    @ApiOperation("删除一个用户")
    @DeleteMapping("/{userId}")
    public ResponseVo deleteById(@PathVariable Long userId) {
        userService.deleteByUserId(userId);
        return ResultUtil.success("删除成功!");
    }

    // // 修改一个用户(后台)
    // @ApiOperation("修改一个用户(后台)")
    // @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "用户自定义名称", required = true, dataType = "String"),
    //         @ApiImplicitParam(name = "password", value = "用户密码", required = true, dataType = "String"),
    //         @ApiImplicitParam(name = "email", value = "用户自定义密码", required = true, dataType = "String")})
    // @PutMapping("/{userId}")
    // public ResponseVo update(@PathVariable Long userId, String name, String password, String email) {
    //     userService.update(userId, name, password, email);
    //     return ResultUtil.success("修改成功!");
    // }


    // 修改一个用户(前台)
    @ApiOperation("修改一个用户(前台)")
    @PutMapping("/{userId}")
    public ResponseVo update(@PathVariable Long userId, String name, String description) {
        userService.update(userId, name, description);
        return ResultUtil.success("修改成功!");
    }

    // 查询所有用户的用户信息(后台)
    @ApiOperation("查询所有用户的用户信息(后台)")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "开始页面", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, dataType = "int")})
    @GetMapping("")
    public PageResultVo list(@RequestParam(defaultValue = PageConfig.PAGE_NUM) int pageNum,
                             @RequestParam(defaultValue = PageConfig.PAGE_SIZE) int pageSize) {
        return ResultUtil.table(userService.listAll(pageNum, pageSize), userService.countAll());
    }

    // 查询某一个用户的信息
    @ApiOperation("查询某一个用户的信息")
    @GetMapping("/{userId}")
    public ResponseVo find(@PathVariable Long userId) {
        return ResultUtil.success("查询成功!", userService.findById(userId));
    }

    // 查询某一个用户的主页信息
    @ApiOperation("查询某一个用户的主页信息")
    @GetMapping("/home/{userId}")
    public ResponseVo home(@PathVariable Long userId, @RequestParam(required = false) Long visitUserId) {
        return ResultUtil.success("查询成功!", userService.findUserHomeInfoById(userId, visitUserId));
    }

    // 更改头像测试类
    @PostMapping("/avatar/{userId}")
    public ResponseVo uploadAvatar(@PathVariable Long userId, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                File path = new File("src/main/resources/static/avatar/" + userId + "/");
                if (!path.exists()) {
                    // 不存在则先创建出来
                    path.mkdirs();
                }
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(path, "avatar.jpg")));
                out.write(file.getBytes());
                out.flush();
                out.close();

                // 设置用户头像地址
                userService.updateAvatarById(userId, "localhost/avatar/" + userId + "/avatar.jpg");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return ResultUtil.success("上传失败," + e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                return ResultUtil.success("上传失败," + e.getMessage());
            }
            return ResultUtil.success("上传成功");
        } else {
            return ResultUtil.success("上传失败，因为文件是空的.");
        }
    }
}
