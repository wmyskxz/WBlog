package wmyskxz.blog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wmyskxz.blog.module.vo.base.ResponseVo;

/**
 * 私信控制器
 *
 * @auth:wmyskxz
 * @date:2019/02/28 - 19:04
 */
@Controller
@RequestMapping("/apis/message")
public class MessageController {

    // 给某个用户发送一条私信
    @PostMapping("/{userId}")
    public ResponseVo sendMessage(@PathVariable Long userId) {
        return null;
    }

    // 删除与某一个用户的私信列表
    @DeleteMapping("/{userId}")
    public ResponseVo deleteMessage(@PathVariable Long userId) {
        return null;
    }

    // 查询某个用户的私信列表
    @GetMapping("/{userId}")
    public ResponseVo listByUserId(@PathVariable Long userId) {
        return null;
    }

    // 查询与某个用户之间的私信记录
    @GetMapping("/{counterpartId}")
    public ResponseVo listByCounterpartId(@PathVariable Long counterpartId) {
        return null;
    }
}
