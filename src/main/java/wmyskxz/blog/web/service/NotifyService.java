package wmyskxz.blog.web.service;

import wmyskxz.blog.module.vo.FollowVo;
import wmyskxz.blog.module.vo.NotifyVo;
import wmyskxz.blog.module.vo.UserFollowVo;
import wmyskxz.blog.module.vo.VoteVo;

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
     * 个人消息页 - 获取用户的点赞信息
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<VoteVo> listUserVotesByUserId(Long userId, int pageNum, int pageSize);

    /**
     * 个人消息页(关注页)
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<UserFollowVo> listUserFollowsByUserId(Long userId, int pageNum, int pageSize);

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
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<UserFollowVo> listUserFansByUserId(Long userId, int pageNum, int pageSize);

    /**
     * 统计用户粉丝的总数
     *
     * @param userId
     * @return
     */
    Long countUserFansByUserId(Long userId);

    /**
     * 统计用户的点赞消息总数
     *
     * @param userId
     * @return
     */
    Long countUserVotesByUserId(Long userId);

    /**
     * 消息页(关注通知信息)
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<FollowVo> listUserFollowNotifyByUserId(Long userId, int pageNum, int pageSize);

    /**
     * 把用户所有type类型的未读消息设置为已读
     *
     * @param userId
     * @param type
     */
    void readAllByUserIdAndType(Long userId, String type);


}
