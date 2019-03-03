package wmyskxz.blog.web.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wmyskxz.blog.config.PageConfig;
import wmyskxz.blog.module.dao.*;
import wmyskxz.blog.module.entity.*;
import wmyskxz.blog.module.vo.NotifyVo;
import wmyskxz.blog.module.vo.UserFollowVo;
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

    @Resource
    UserMapper userMapper;
    @Resource
    NotifyMapper notifyMapper;
    @Resource
    UserFollowMapper userFollowMapper;


    @Override
    @Transactional// 开启事务
    public NotifyVo countByUserId(Long userId) {
        NotifyVo resultObject = new NotifyVo();

        NotifyExample notifyExample = new NotifyExample();
        notifyExample.or().andIsReadEqualTo(false);
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
    public List<UserFollowVo> listUserFollowsByUserId(Long userId) {

        List<UserFollowVo> resultList = new LinkedList<>();

        UserFollowExample userFollowExample = new UserFollowExample();
        userFollowExample.or().andUserIdEqualTo(userId);
        PageHelper.startPage(PageConfig.PAGE_NUM, PageConfig.FOLLOW_AND_FAN_PAGE_SIZE);// 只对下一行查询有效
        List<UserFollow> userFollows = userFollowMapper.selectByExample(userFollowExample);

        // 拼接数据
        UserFollowVo userFollowVo;
        for (UserFollow userFollow : userFollows) {
            userFollowVo = new UserFollowVo();
            User followUser = userMapper.selectByPrimaryKey(userFollow.getFollowUserId());
            // 把FollowUser中同UserFollowVo中相同名称字段的数据copy到后者当中去
            BeanUtils.copyProperties(followUser, userFollowVo);
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
    public List<UserFollowVo> listUserFansByUserId(Long userId) {
        List<UserFollowVo> resultList = new LinkedList<>();

        UserFollowExample userFollowExample = new UserFollowExample();
        userFollowExample.or().andFollowUserIdEqualTo(userId);
        PageHelper.startPage(PageConfig.PAGE_NUM, PageConfig.FOLLOW_AND_FAN_PAGE_SIZE);// 只对下一行查询有效
        List<UserFollow> userFans = userFollowMapper.selectByExample(userFollowExample);

        // 拼接数据
        UserFollowVo userFollowVo;
        for (UserFollow userFollow : userFans) {
            userFollowVo = new UserFollowVo();
            User fanUser = userMapper.selectByPrimaryKey(userFollow.getFollowUserId());
            // 把FollowUser中同UserFollowVo中相同名称字段的数据copy到后者当中去
            BeanUtils.copyProperties(fanUser, userFollowVo);
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

}
