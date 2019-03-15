package wmyskxz.blog.web.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wmyskxz.blog.module.dao.*;
import wmyskxz.blog.module.entity.*;
import wmyskxz.blog.module.vo.UserInfoVo;
import wmyskxz.blog.util.ConstCode;
import wmyskxz.blog.util.PasswordHelper;
import wmyskxz.blog.web.service.UserService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * UserService实现类
 *
 * @auth:wmyskxz
 * @date:2019/02/27 - 16:02
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource UserMapper userMapper;
    @Resource NotifyMapper notifyMapper;
    @Resource UserFollowMapper userFollowMapper;
    @Resource BlogInfoMapper blogInfoMapper;
    @Resource VoteMapper voteMapper;

    @Override
    @Transactional// 开启事务
    public void register(String username, String password, String email) {
        User user = new User();
        user.setAvatar(ConstCode.DEFAULT_AVATAR);
        user.setName(username);// 默认名字与登录账户相同
        user.setUsername(username);
        user.setPassword(password);
        PasswordHelper.encryptPassword(user);
        user.setEmail(email);
        userMapper.insertSelective(user);
    }

    @Override
    @Transactional// 开启事务
    public void deleteByUserId(Long userId) {
        User user = new User();
        user.setStatus(false);
        UserExample userExample = new UserExample();
        userExample.or().andIdEqualTo(userId);

        userMapper.updateByExampleSelective(user, userExample);
    }

    @Override
    @Transactional// 开启事务
    public void deleteByUserIds(Long... userIds) {
        for (Long userId : userIds) {
            deleteByUserId(userId);
        }   // end for
    }

    @Override
    @Transactional// 开启事务
    public void update(Long userId, String name, String password, String email) {
        User user = userMapper.selectByPrimaryKey(userId);
        user.setName(name);
        PasswordHelper.encryptPassword(user);
        user.setEmail(email);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    @Transactional// 开启事务
    public void updateUserNameById(Long userId, String name) {
        User user = new User();
        user.setUsername(name);
        UserExample userExample = new UserExample();
        userExample.or().andIdEqualTo(userId);
        userMapper.updateByExampleSelective(user, userExample);
    }

    @Override
    @Transactional// 开启事务
    public void updatePasswordById(Long userId, String password) {
        User user = new User();
        user.setPassword(password);
        PasswordHelper.encryptPassword(user);
        UserExample userExample = new UserExample();
        userExample.or().andIdEqualTo(userId);
        userMapper.updateByExampleSelective(user, userExample);
    }

    @Override
    @Transactional// 开启事务
    public void updateAvatarById(Long userId, String avatar) {
        User user = new User();
        user.setAvatar(avatar);
        UserExample userExample = new UserExample();
        userExample.or().andIdEqualTo(userId);
        userMapper.updateByExampleSelective(user, userExample);
    }

    @Override
    @Transactional// 开启事务
    public UserInfoVo findById(Long userId) {
        UserInfoVo resultObject = new UserInfoVo();
        User user = userMapper.selectByPrimaryKey(userId);
        BeanUtils.copyProperties(user, resultObject);
        resultObject.setUserId(userId);
        return resultObject;
    }

    @Override
    @Transactional// 开启事务
    public User findByUsername(String username) {
        UserExample userExample = new UserExample();
        userExample.or().andNameEqualTo(username);
        return userMapper.selectByExample(userExample).get(0);
    }

    @Override
    @Transactional// 开启事务
    public void updateLastLoginTime(User user) {
        user.setLastLoginTime(new Date());// 设置为当前时间
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    @Transactional// 开启事务
    public List<User> listAll(int pageNum, int pageSize) {

        List<User> resultList = new LinkedList<>();

        UserExample userExample = new UserExample();
        userExample.or();// 无条件查询即查询所有
        PageHelper.startPage(pageNum, pageSize);// 只对下一行查询有效
        resultList = userMapper.selectByExample(userExample);

        return resultList;
    }

    @Override
    @Transactional// 开启事务
    public Long countAll() {
        UserExample userExample = new UserExample();
        userExample.or();// 无条件查询即查询所有
        Long count = userMapper.countByExample(userExample);

        return count;
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
        vote.setNotifyId(notify.getId());
        voteMapper.insertSelective(vote);

        // 3.将对应博客的点赞数+1
        BlogInfo blogInfo = blogInfoMapper.selectByPrimaryKey(blogId);
        blogInfo.setVoteSize(blogInfo.getVoteSize() + 1);
        blogInfoMapper.updateByPrimaryKeySelective(blogInfo);
    }

    @Override
    @Transactional// 开启事务
    public void unVote(Long userId, Long blogId) {
        VoteExample voteExample = new VoteExample();
        voteExample.or().andBlogIdEqualTo(blogId).andUserIdEqualTo(userId);
        voteMapper.deleteByExample(voteExample);

        // 再把对应博文的点赞数-1
        BlogInfo blogInfo = blogInfoMapper.selectByPrimaryKey(blogId);
        blogInfo.setVoteSize(blogInfo.getVoteSize() - 1);
        blogInfoMapper.updateByPrimaryKeySelective(blogInfo);
    }
}
