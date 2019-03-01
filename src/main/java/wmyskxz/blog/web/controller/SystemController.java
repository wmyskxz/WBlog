package wmyskxz.blog.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wmyskxz.blog.module.entity.User;
import wmyskxz.blog.module.vo.base.ResponseVo;
import wmyskxz.blog.web.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 系统相关控制器
 *
 * @auth:wmyskxz
 * @date:2019/02/28 - 15:03
 */
@Controller
public class SystemController {

    @Resource
    UserService userService;

    /** 提交注册 */
    @PostMapping("/register")
    @ResponseBody
    public ResponseVo register(HttpServletRequest request, User registerUser, String confirmPassword, String verification){
        // //判断验证码
        // String rightCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        // if (StringUtils.isNotBlank(verification) && StringUtils.isNotBlank(rightCode) && verification.equals(rightCode)) {
        //     //验证码通过
        // } else {
        //     return ResultUtil.error("验证码错误！");
        // }
        String username = registerUser.getUsername();
        User user = userService.getUserByUsername(username);
        if (null != user) {
            // return ResultUtil.error("用户名已存在！");
        }
        String password = registerUser.getPassword();
        // 判断两次输入密码是否相等
        if (confirmPassword != null && password != null) {
            if (!confirmPassword.equals(password)) {
                // return ResultUtil.error("两次密码不一致！");
            }
        }
        // registerUser.setSalt();
        Date date = new Date();
        // PasswordHelper.encryptPassword(registerUser);
        // 注册
        // int registerResult = userService.register(registerUser);
        // if(registerResult > 0){
        //     return ResultUtil.success("注册成功！");
        // }else {
        //     return ResultUtil.error("注册失败，请稍后再试！");
        // }
        return null;
    }

    /** 提交登录 */
    @PostMapping("/login")
    @ResponseBody
    public ResponseVo login(HttpServletRequest request, String username, String password, @RequestParam(value = "rememberMe", defaultValue = "0") Integer rememberMe) {
        // 判断验证码
        // String rightCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        // if (StringUtils.isNotBlank(verification) && StringUtils.isNotBlank(rightCode) && verification.equals(rightCode)) {
        //     // 验证码通过
        // } else {
        //     return ResultUtil.error("验证码错误！");
        // }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            token.setRememberMe(1 == rememberMe);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
        } catch (LockedAccountException e) {
            token.clear();
            // return ResultUtil.error("用户已经被锁定不能登录，请联系管理员！");
        } catch (AuthenticationException e) {
            token.clear();
            // return ResultUtil.error("用户名或者密码错误！");
        }
        // 更新最后登录时间
        userService.updateLastLoginTime((User) SecurityUtils.getSubject().getPrincipal());
        // return ResultUtil.success("登录成功！");

        System.out.println("登陆成功!");
        return null;
    }
}
