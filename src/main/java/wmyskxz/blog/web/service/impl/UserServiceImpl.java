package wmyskxz.blog.web.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wmyskxz.blog.module.dao.*;
import wmyskxz.blog.module.entity.*;
import wmyskxz.blog.module.vo.UserInfoVo;
import wmyskxz.blog.util.ConstCode;
import wmyskxz.blog.web.service.UserService;

import javax.annotation.Resource;

/**
 * UserService实现类
 *
 * @auth:wmyskxz
 * @date:2019/02/27 - 16:02
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;
    @Resource
    NotifyMapper notifyMapper;
    @Resource
    UserFollowMapper userFollowMapper;
    @Resource
    BlogInfoMapper blogInfoMapper;
    @Resource
    VoteMapper voteMapper;

    @Override
    @Transactional// 开启事务
    public void register(String name, String password, String email) {
        User user = new User();
        user.setAvatar(ConstCode.DEFAULT_AVATAR);
        user.setName(name);
        user.setUsername(name);
        user.setPassword(password);
        user.setEmail(email);
    }

    @Override
    @Transactional// 开启事务
    public void deleteUserByUserId(Long userId) {
        User user = new User();
        user.setStatus(false);
        UserExample userExample = new UserExample();
        userExample.or().andIdEqualTo(userId);

        userMapper.updateByExampleSelective(user, userExample);
    }

    @Override
    @Transactional// 开启事务
    public void deleteUsers(Long... userIds) {
        for (Long userId : userIds) {
            deleteUserByUserId(userId);
        }   // end for
    }

    @Override
    @Transactional// 开启事务
    public void updateUsernameByUserId(Long userId, String username) {
        User user = new User();
        user.setUsername(username);
        UserExample userExample = new UserExample();
        userExample.or().andIdEqualTo(userId);
        userMapper.updateByExampleSelective(user, userExample);
    }

    @Override
    @Transactional// 开启事务
    public void updateUserPasswordByUserId(Long userId, String password) {
        User user = new User();
        user.setPassword(password);
        UserExample userExample = new UserExample();
        userExample.or().andIdEqualTo(userId);
        userMapper.updateByExampleSelective(user, userExample);
    }

    @Override
    @Transactional// 开启事务
    public void updateUserAvatarByUserId(Long userId, String avatar) {
        User user = new User();
        user.setAvatar(avatar);
        UserExample userExample = new UserExample();
        userExample.or().andIdEqualTo(userId);
        userMapper.updateByExampleSelective(user, userExample);
    }

    @Override
    @Transactional// 开启事务
    public UserInfoVo getUserInfoByUserId(Long userId) {
        UserInfoVo resultObject = new UserInfoVo();
        User user = userMapper.selectByPrimaryKey(userId);
        BeanUtils.copyProperties(user, resultObject);
        return resultObject;
    }

    @Override
    @Transactional// 开启事务
    public User getUserByName(String name) {
        UserExample userExample = new UserExample();
        userExample.or().andNameEqualTo(name);
        return userMapper.selectByExample(userExample).get(0);
    }

    @Override
    @Transactional// 开启事务
    public void follow(Long userAId, Long userBId) {
        // 1.创建相应的通知信息
        Notify notify = new Notify();
        notify.setSenderId(userAId);
        notify.setRecevierId(userBId);
        notify.setType(ConstCode.NOTIFY_FOLLOW_TYPE);
        notifyMapper.insertSelective(notify);

        // 2.创建相应的user_follow表信息
        UserFollow userFollow = new UserFollow();
        userFollow.setUserId(userAId);
        userFollow.setFollowUserId(userBId);
        userFollowMapper.insertSelective(userFollow);
    }

    @Override
    @Transactional// 开启事务
    public void unFollow(Long userAId, Long userBId) {
        UserFollowExample userFollowExample = new UserFollowExample();
        userFollowExample.or().andUserIdEqualTo(userAId).andFollowUserIdEqualTo(userBId);
        userFollowMapper.deleteByExample(userFollowExample);
    }

    @Override
    @Transactional// 开启事务
    public void vote(Long userId, Long blogId) {
        // 1.创建相应的Notify通知
        Notify notify = new Notify();
        notify.setType(ConstCode.NOTIFY_VOTE_TYPE);
        notify.setSenderId(userId);
        notify.setRecevierId(blogInfoMapper.selectByPrimaryKey(blogId).getUserId());
        notifyMapper.insertSelective(notify);

        // 2.创建对应的vote表信息
        Vote vote = new Vote();
        vote.setUserId(userId);
        vote.setBlogId(blogId);
        voteMapper.insertSelective(vote);
    }

    @Override
    @Transactional// 开启事务
    public void unVote(Long userId, Long blogId) {
        VoteExample voteExample = new VoteExample();
        voteExample.or().andBlogIdEqualTo(blogId).andUserIdEqualTo(userId);
        voteMapper.deleteByExample(voteExample);
    }
}
