package wmyskxz.blog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import wmyskxz.blog.module.vo.base.ResponseVo;

/**
 * 通知消息控制器
 *
 * @auth:wmyskxz
 * @date:2019/02/28 - 19:10
 */
@Controller
@RequestMapping("/apis/notify")
public class NotifyController {

    // 管理员发送一条通知
    @PostMapping("")
    public ResponseVo sendNotify() {
        return null;
    }

    // 查询某个用户的通知消息
    @GetMapping("/{userId}")
    public ResponseVo listByUserId(@PathVariable Long userId) {
        return null;
    }
}
