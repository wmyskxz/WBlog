package wmyskxz.blog.web.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wmyskxz.blog.module.dao.*;
import wmyskxz.blog.module.entity.*;
import wmyskxz.blog.module.vo.FollowVo;
import wmyskxz.blog.module.vo.NotifyVo;
import wmyskxz.blog.module.vo.UserFollowVo;
import wmyskxz.blog.module.vo.VoteVo;
import wmyskxz.blog.util.ConstCode;
import wmyskxz.blog.web.service.NotifyService;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * NotifyService实现类
 *
 * @auth:wmyskxz
 * @date:2019/02/28 - 09:03
 */
@Service
public class NotifyServiceImpl implements NotifyService {

    @Resource UserMapper userMapper;
    @Resource NotifyMapper notifyMapper;
    @Resource UserFollowMapper userFollowMapper;
    @Resource BlogInfoMapper blogInfoMapper;
    @Resource VoteMapper voteMapper;

    @Override
    @Transactional// 开启事务
    public NotifyVo countByUserId(Long userId) {
        NotifyVo resultObject = new NotifyVo();

        NotifyExample notifyExample = new NotifyExample();
        notifyExample.or().andIsReadEqualTo(false).andRecevierIdEqualTo(userId);
        List<Notify> notifyList = notifyMapper.selectByExample(notifyExample);

        // 根据不同类型统计各种通知的数量
        int unreadCommentSize = 0;// 未读评论消息数量
        int unreadMessageSize = 0;// 未读私信消息数量
        int unreadVoteSize = 0;// 未读点赞消息数量
        int unreadFollowSize = 0;// 未读关注消息数量
        for (Notify notify : notifyList) {
            if (notify.getType().equals(ConstCode.NOTIFY_COMMENT_TYPE)) {
                unreadCommentSize++;
            } else if (notify.getType().equals(ConstCode.NOTIFY_MESSAGE_TYPE)) {
                unreadMessageSize++;
            } else if (notify.getType().equals(ConstCode.NOTIFY_VOTE_TYPE)) {
                unreadVoteSize++;
            } else if (notify.getType().equals(ConstCode.NOTIFY_FOLLOW_TYPE)) {
                unreadFollowSize++;
            }
        }   // end for:统计各个种类的消息数量完成

        resultObject.setUnreadCommentSize(unreadCommentSize);
        resultObject.setUnreadMessageSize(unreadMessageSize);
        resultObject.setUnreadFollowSize(unreadFollowSize);
        resultObject.setUnreadVoteSize(unreadVoteSize);

        return resultObject;
    }

    @Override
    @Transactional// 开启事务
    public List<VoteVo> listUserVotesByUserId(Long userId, int pageNum, int pageSize) {

        List<VoteVo> resultList = new LinkedList<>();

        NotifyExample notifyExample = new NotifyExample();
        notifyExample.or().andRecevierIdEqualTo(userId).andTypeEqualTo("vote");
        PageHelper.startPage(pageNum, pageSize);// 只对下一行的查询生效
        List<Notify> notifyList = notifyMapper.selectByExample(notifyExample);

        // 拼接数据
        VoteVo voteVo;
        for (Notify notify : notifyList) {
            voteVo = new VoteVo();
            // 查询发送者的用户信息
            User user = userMapper.selectByPrimaryKey(notify.getSenderId());
            voteVo.setAvatar(user.getAvatar());
            voteVo.setUserId(user.getId());
            voteVo.setUsername(user.getUsername());
            // 查询点赞博文信息
            VoteExample voteExample = new VoteExample();
            voteExample.or().andNotifyIdEqualTo(notify.getId());
            Vote vote = voteMapper.selectByExample(voteExample).get(0);
            BlogInfo blogInfo = blogInfoMapper.selectByPrimaryKey(vote.getBlogId());
            voteVo.setBlogId(vote.getBlogId());
            voteVo.setBlogTitle(blogInfo.getTitle());
            // 设置时间
            voteVo.setCreateTime(notify.getCreateTime());
            resultList.add(voteVo);
        }

        return resultList;
    }

    @Override
    @Transactional// 开启事务
    public List<UserFollowVo> listUserFollowsByUserId(Long userId, int pageNum, int pageSize) {

        List<UserFollowVo> resultList = new LinkedList<>();

        UserFollowExample userFollowExample = new UserFollowExample();
        userFollowExample.or().andUserIdEqualTo(userId);
        userFollowExample.setOrderByClause("create_time DESC");// 按照创建时间降序排列
        PageHelper.startPage(pageNum, pageSize);// 只对下一行查询有效
        List<UserFollow> userFollows = userFollowMapper.selectByExample(userFollowExample);

        // 拼接数据
        UserFollowVo userFollowVo;
        for (UserFollow userFollow : userFollows) {
            userFollowVo = new UserFollowVo();
            User followUser = userMapper.selectByPrimaryKey(userFollow.getFollowUserId());
            // 把FollowUser中同UserFollowVo中相同名称字段的数据copy到后者当中去
            BeanUtils.copyProperties(followUser, userFollowVo);
            userFollowVo.setUserId(followUser.getId());
            // 查询文章数量
            BlogInfoExample blogInfoExample = new BlogInfoExample();
            blogInfoExample.or().andUserIdEqualTo(followUser.getId());
            userFollowVo.setBlogSize(Math.toIntExact(blogInfoMapper.countByExample(blogInfoExample)));
            // 查询followUser是否关注自己
            userFollowExample = new UserFollowExample();
            userFollowExample.or().andUserIdEqualTo(followUser.getId()).andFollowUserIdEqualTo(userId);
            // 如果没有关注
            if (userFollowMapper.selectByExample(userFollowExample).isEmpty()) {
                userFollowVo.setFollowed(false);
            } else {
                userFollowVo.setFollowed(true);
            }   // end if

            resultList.add(userFollowVo);
        }

        return resultList;
    }

    @Override
    @Transactional// 开启事务
    public List<UserFollowVo> listUserFansByUserId(Long userId, int pageNum, int pageSize) {
        List<UserFollowVo> resultList = new LinkedList<>();

        UserFollowExample userFollowExample = new UserFollowExample();
        userFollowExample.or().andFollowUserIdEqualTo(userId);
        userFollowExample.setOrderByClause("create_time DESC");// 按照创建时间降序排列
        PageHelper.startPage(pageNum, pageSize);// 只对下一行查询有效
        List<UserFollow> userFans = userFollowMapper.selectByExample(userFollowExample);

        // 拼接数据
        UserFollowVo userFollowVo;
        for (UserFollow userFollow : userFans) {
            userFollowVo = new UserFollowVo();
            User fanUser = userMapper.selectByPrimaryKey(userFollow.getUserId());
            // 把FollowUser中同UserFollowVo中相同名称字段的数据copy到后者当中去
            BeanUtils.copyProperties(fanUser, userFollowVo);
            userFollowVo.setUserId(fanUser.getId());
            // 查询文章数量
            BlogInfoExample blogInfoExample = new BlogInfoExample();
            blogInfoExample.or().andUserIdEqualTo(fanUser.getId());
            userFollowVo.setBlogSize(Math.toIntExact(blogInfoMapper.countByExample(blogInfoExample)));
            // 查询自己是否关注了fanUser
            userFollowExample = new UserFollowExample();
            userFollowExample.or().andUserIdEqualTo(userId).andFollowUserIdEqualTo(fanUser.getId());
            // 如果没有关注
            if (userFollowMapper.selectByExample(userFollowExample).isEmpty()) {
                userFollowVo.setFollowed(false);
            } else {
                userFollowVo.setFollowed(true);
            }   // end if

            resultList.add(userFollowVo);
        }

        return resultList;
    }

    @Override
    @Transactional// 开启事务
    public Long countUserFollowsByUserId(Long userId) {

        UserFollowExample userFollowExample = new UserFollowExample();
        userFollowExample.or().andUserIdEqualTo(userId);

        return userFollowMapper.countByExample(userFollowExample);
    }

    @Override
    @Transactional// 开启事务
    public Long countUserFansByUserId(Long userId) {

        UserFollowExample userFollowExample = new UserFollowExample();
        userFollowExample.or().andFollowUserIdEqualTo(userId);

        return userFollowMapper.countByExample(userFollowExample);
    }

    @Override
    @Transactional// 开启事务
    public Long countUserVotesByUserId(Long userId) {

        NotifyExample notifyExample = new NotifyExample();
        notifyExample.or().andRecevierIdEqualTo(userId).andTypeEqualTo("vote");

        return notifyMapper.countByExample(notifyExample);
    }

    @Override
    @Transactional// 开启事务
    public List<FollowVo> listUserFollowNotifyByUserId(Long userId, int pageNum, int pageSize) {

        List<FollowVo> resultList = new LinkedList<>();

        UserFollowExample userFollowExample = new UserFollowExample();
        userFollowExample.or().andFollowUserIdEqualTo(userId);
        PageHelper.startPage(pageNum, pageSize);// 只对下一行查询生效
        List<UserFollow> userFollows = userFollowMapper.selectByExample(userFollowExample);

        // 拼接数据
        FollowVo followVo;
        for (UserFollow userFollow : userFollows) {
            followVo = new FollowVo();
            User followUser = userMapper.selectByPrimaryKey(userFollow.getUserId());
            followVo.setAvatar(followUser.getAvatar());
            followVo.setUserId(followUser.getId());
            followVo.setUsername(followUser.getUsername());
            followVo.setCreateTime(userFollow.getCreateTime());
            // 查询是否关注该用户
            userFollowExample = new UserFollowExample();
            userFollowExample.or().andUserIdEqualTo(userId).andFollowUserIdEqualTo(followUser.getId());

            if (userFollowMapper.selectByExample(userFollowExample).isEmpty()) {
                followVo.setFollow(false);
            } else followVo.setFollow(true);

            resultList.add(followVo);
        }

        return resultList;
    }

    @Override
    @Transactional// 开启事务
    public void readAllByUserIdAndType(Long userId, String type) {
        NotifyExample notifyExample = new NotifyExample();
        notifyExample.or().andRecevierIdEqualTo(userId).andIsReadEqualTo(false).andTypeEqualTo(type);
        List<Notify> notifyList = notifyMapper.selectByExample(notifyExample);

        for (Notify notify : notifyList) {
            notify.setIsRead(true);
            notifyMapper.updateByPrimaryKeySelective(notify);
        }   // end for
    }
}
