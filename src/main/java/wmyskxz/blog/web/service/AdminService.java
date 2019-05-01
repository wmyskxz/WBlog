package wmyskxz.blog.web.service;

import wmyskxz.blog.module.vo.AdminHomeVo;

/**
 * 后台管理相关Service
 *
 * @auth:wmyskxz
 * @date:2019/04/10 - 08:15
 */
public interface AdminService {
    /**
     * 后台首页（数据统计页）
     *
     * @return
     */
    AdminHomeVo home();
}
