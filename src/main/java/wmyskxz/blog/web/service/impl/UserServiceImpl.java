package wmyskxz.blog.web.service.impl;

import com.github.pagehelper.PageHelper;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wmyskxz.blog.module.dao.*;
import wmyskxz.blog.module.entity.*;
import wmyskxz.blog.module.vo.UserHomeVo;
import wmyskxz.blog.module.vo.UserInfoVo;
import wmyskxz.blog.util.ConstCode;
import wmyskxz.blog.util.PasswordHelper;
import wmyskxz.blog.web.service.UserService;

import javax.annotation.Resource;
import java.util.*;

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
    @Autowired RedisSessionDAO redisSessionDAO;
    @Autowired SessionManager sessionManager;
    @Autowired RedisCacheManager redisCacheManager;

    @Override
    @Transactional// 开启事务
    public Boolean checkEmail(String email) {
        UserExample userExample = new UserExample();
        userExample.or().andEmailEqualTo(email);

        return userMapper.selectByExample(userExample).isEmpty();
    }

    @Override
    @Transactional// 开启事务
    public Boolean checkUsername(String username) {
        UserExample userExample = new UserExample();
        userExample.or().andUsernameEqualTo(username);

        return userMapper.selectByExample(userExample).isEmpty();
    }

    @Override
    @Transactional// 开启事务
    public Boolean checkUserName(String name) {
        UserExample userExample = new UserExample();
        userExample.or().andNameEqualTo(name);

        return userMapper.selectByExample(userExample).isEmpty();
    }

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
    public void update(Long userId, String name, String description) {
        User user = userMapper.selectByPrimaryKey(userId);
        user.setName(name);
        user.setDescription(description);
        userMapper.updateByPrimaryKey(user);
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
        userExample.or().andUsernameEqualTo(username);
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

    @Override
    @Transactional// 开启事务
    public UserHomeVo findUserHomeInfoById(Long userId, Long visitUserId) {

        UserHomeVo resultObject = new UserHomeVo();
        User user = userMapper.selectByPrimaryKey(userId);
        // 把user同resultObject相同的字段赋值给后者
        // avatar/name/fanSize/followSize/voteSize/description
        // 还剩下isFollow/blogSize/userId没有设置
        BeanUtils.copyProperties(user, resultObject);
        // 设置userId
        resultObject.setUserId(user.getId());
        // 查询blogSize
        BlogInfoExample blogInfoExample = new BlogInfoExample();
        blogInfoExample.or().andUserIdEqualTo(userId);
        Long count = blogInfoMapper.countByExample(blogInfoExample);
        resultObject.setBlogSize(Math.toIntExact(count));
        // 查询是否关注该用户
        if (null == visitUserId || userId.equals(visitUserId)) {
            // 如果没有登录,null表示没有登录 or 自己访问自己
            resultObject.setFollow(false);
        } else {
            UserFollowExample userFollowExample = new UserFollowExample();
            userFollowExample.or().andUserIdEqualTo(visitUserId).andFollowUserIdEqualTo(userId);
            if (userFollowMapper.selectByExample(userFollowExample).isEmpty()) {
                // 为空则表明没有关注该用户
                resultObject.setFollow(false);
            } else resultObject.setFollow(true);
        }   // end if

        return resultObject;
    }

    @Override
    public List<User> selectOnlineUsers() {
        // 因为我们是用redis实现了shiro的session的Dao,而且是采用了shiro+redis这个插件
        // 所以从spring容器中获取redisSessionDAO
        // 来获取session列表.
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        Iterator<Session> it = sessions.iterator();
        List<User> onlineUserList = new ArrayList<User>();
        // 遍历session
        while (it.hasNext()) {
            // 这是shiro已经存入session的
            // 现在直接取就是了
            Session session = it.next();
            //标记为已提出的不加入在线列表
            if (session.getAttribute("kickout") != null) {
                continue;
            }
            User onlineUser = getSessionBo(session);
            if (onlineUser != null) {
                /*用户名搜索*/
                // if (StringUtils.isNotBlank(userVo.getUsername())) {
                //     if (onlineUser.getUsername().contains(userVo.getUsername())) {
                //         onlineUserList.add(onlineUser);
                //     }
                // } else {
                //     onlineUserList.add(onlineUser);
                // }
                onlineUserList.add(onlineUser);
            }
        }
        return onlineUserList;
    }

    private User getSessionBo(Session session) {
        //获取session登录信息。
        Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        if (null == obj) {
            return null;
        }
        //确保是 SimplePrincipalCollection对象。
        if (obj instanceof SimplePrincipalCollection) {
            SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
            obj = spc.getPrimaryPrincipal();
            if (null != obj && obj instanceof User) {
                User user = (User) obj;
                //存储session + user 综合信息
                User userBo = new User();
                //最后一次和系统交互的时间
                // userBo.setLastAccess(session.getLastAccessTime());
                //主机的ip地址
                userBo.setLoginIpAddress(user.getLoginIpAddress());
                //session ID
                // userBo.set(session.getId().toString());
                //最后登录时间
                userBo.setLastLoginTime(user.getLastLoginTime());
                //回话到期 ttl(ms)
                // userBo.setTimeout(session.getTimeout());
                //session创建时间
                // userBo.setStartTime(session.getStartTimestamp());
                //是否踢出
                // userBo.setSessionStatus(false);
                /*用户名*/
                userBo.setUsername(user.getUsername());
                return userBo;
            }
        }
        return null;
    }
}
