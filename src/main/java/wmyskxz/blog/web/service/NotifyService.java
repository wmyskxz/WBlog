package wmyskxz.blog.web.service;

import wmyskxz.blog.module.vo.NotifyVo;
import wmyskxz.blog.module.vo.UserFollowVo;

import java.util.List;

/**
 * Notify通知相关的Service
 *
 * @auth:wmyskxz
 * @date:2019/02/28 - 08:05
 */
public interface NotifyService {
    /**
     * 主页/个人消息页 - 获取用户的通知数量
     *
     * @param userId
     * @return
     */
    NotifyVo countByUserId(Long userId);

    /**
     * 个人消息页(关注页)
     *
     * @param userId
     * @return
     */
    List<UserFollowVo> listUserFollowsByUserId(Long userId);

    /**
     * 统计用户关注用户的总数
     *
     * @param userId
     * @return
     */
    Long countUserFollowsByUserId(Long userId);

    /**
     * 个人消息页(粉丝页)
     *
     * @param userId
     * @return
     */
    List<UserFollowVo> listUserFansByUserId(Long userId);

    /**
     * 统计用户粉丝的总数
     *
     * @param userId
     * @return
     */
    Long countUserFansByUserId(Long userId);
}
