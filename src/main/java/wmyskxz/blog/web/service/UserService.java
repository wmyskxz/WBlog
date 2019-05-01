package wmyskxz.blog.web.service;

import wmyskxz.blog.module.entity.User;
import wmyskxz.blog.module.vo.UserHomeVo;
import wmyskxz.blog.module.vo.UserInfoVo;

import java.util.List;

/**
 * 用户相关Service
 *
 * @auth:wmyskxz
 * @date:2019/02/26 - 15:19
 */
public interface UserService {

    /**
     * 查询在线用户
     *
     * @return
     */
    List<User> selectOnlineUsers();

    /**
     * 注册 - 检查用户Email是否可用
     *
     * @param email
     * @return
     */
    Boolean checkEmail(String email);

    /**
     * 注册 - 检查用户账号是否可用
     *
     * @param username
     * @return
     */
    Boolean checkUsername(String username);

    /**
     * 检查用户自定义的名称是否可用
     *
     * @param name
     * @return
     */
    Boolean checkUserName(String name);

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
    void deleteByUserId(Long userId);

    /**
     * 批量删除用户
     *
     * @param userIds
     */
    void deleteByUserIds(Long... userIds);

    /**
     * 修改用户信息(后台) - 修改名字、密码orEmail
     *
     * @param userId
     */
    void update(Long userId, String name, String password, String email);

    /**
     * 修改用户信息(前台) - 修改自定义名称and自我介绍
     *
     * @param userId
     * @param name
     * @param description
     */
    void update(Long userId, String name, String description);

    /**
     * 用户修改自定义名称
     *
     * @param userId
     * @param name
     */
    void updateUserNameById(Long userId, String name);

    /**
     * 用户修改密码操作
     *
     * @param userId
     * @param password
     */
    void updatePasswordById(Long userId, String password);

    /**
     * 用户修改头像
     *
     * @param userId
     * @param avatar
     */
    void updateAvatarById(Long userId, String avatar);

    /**
     * 通过用户id获取用户信息
     *
     * @param userId
     * @return
     */
    UserInfoVo findById(Long userId);

    /**
     * 登录 - 通过账户名查找用户信息
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 用户主页 - 查询该用户的基础信息
     *
     * @param userId
     * @param visitUserId 访问该方法的用户,为空则表明没有登录
     * @return
     */
    UserHomeVo findUserHomeInfoById(Long userId, Long visitUserId);

    /**
     * 更新用户最后登录时间
     *
     * @param user
     */
    void updateLastLoginTime(User user);

    /**
     * 查询所有的用户信息(后台)
     *
     * @return
     */
    List<User> listAll(int pageNum, int pageSize);

    /**
     * 获取用户总数
     *
     * @return
     */
    Long countAll();

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
