package wmyskxz.blog.web.service;

import wmyskxz.blog.module.entity.User;
import wmyskxz.blog.module.vo.UserInfoVo;

/**
 * 用户相关Service
 *
 * @auth:wmyskxz
 * @date:2019/02/26 - 15:19
 */
public interface UserService {
    /**
     * 注册新用户
     *
     * @param username
     * @param password
     * @param email
     */
    void register(String username, String password, String email);

    /**
     * 通过用户id删除用户
     *
     * @param userId
     */
    void deleteUserByUserId(Long userId);

    /**
     * 批量删除用户
     *
     * @param userIds
     */
    void deleteUsers(Long... userIds);

    /**
     * 用户修改自定义名称
     *
     * @param userId
     * @param username
     */
    void updateUsernameByUserId(Long userId, String username);

    /**
     * 用户修改密码操作
     *
     * @param userId
     * @param password
     */
    void updateUserPasswordByUserId(Long userId, String password);

    /**
     * 用户修改头像
     *
     * @param userId
     * @param avatar
     */
    void updateUserAvatarByUserId(Long userId, String avatar);

    /**
     * 通过用户id获取用户信息
     *
     * @param userId
     * @return
     */
    UserInfoVo getUserInfoByUserId(Long userId);

    /**
     * 登录 - 通过账户名查找用户信息
     *
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    //*********************** 用户操作类 ***************************

    /**
     * A用户关注B
     *
     * @param userAId
     * @param userBId
     */
    void follow(Long userAId, Long userBId);

    /**
     * A用户取消关注了B
     *
     * @param userAId
     * @param userBId
     */
    void unFollow(Long userAId, Long userBId);

    /**
     * 点赞
     *
     * @param userId
     * @param blogId
     */
    void vote(Long userId, Long blogId);

    /**
     * 取消点赞
     *
     * @param userId
     * @param blogId
     */
    void unVote(Long userId, Long blogId);
}
