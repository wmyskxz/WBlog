package wmyskxz.blog.web.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wmyskxz.blog.module.dao.BlogInfoMapper;
import wmyskxz.blog.module.dao.CommentMapper;
import wmyskxz.blog.module.dao.NotifyMapper;
import wmyskxz.blog.module.dao.UserMapper;
import wmyskxz.blog.module.entity.*;
import wmyskxz.blog.module.vo.CommentVo;
import wmyskxz.blog.util.ConstCode;
import wmyskxz.blog.web.service.CommentService;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * CommentService实现类
 *
 * @auth:wmyskxz
 * @date:2019/02/27 - 15:28
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource CommentMapper commentMapper;
    @Resource UserMapper userMapper;
    @Resource BlogInfoMapper blogInfoMapper;
    @Resource NotifyMapper notifyMapper;

    @Override
    @Transactional// 开启事务
    public void add(Long userId, Long blogId, String content, Long atId) {

        // 1.先要创建对应的通知消息
        Notify notify = new Notify();
        notify.setSenderId(userId);
        notify.setRecevierId(blogInfoMapper.selectByPrimaryKey(blogId).getUserId());
        notify.setType(ConstCode.NOTIFY_COMMENT_TYPE);
        notifyMapper.insertSelective(notify);

        // 2.创建对应的评论消息
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setBlogId(blogId);
        comment.setContent(content);
        comment.setAtId(atId);
        comment.setNotifyId(notify.getId());
        commentMapper.insertSelective(comment);
    }

    @Override
    @Transactional// 开启事务
    public void reply(Long userId, Long blogId, String content, Long atId, Long blogerId) {

        // 1.先创建对应的通知消息
        Notify notify = new Notify();
        notify.setSenderId(userId);
        notify.setType(ConstCode.NOTIFY_REPLY_TYPE);

        // 这里需要判断是博主回复其他人还是其他人回复其他人
        // 然后创建对应的通知消息和评论消息
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setBlogId(blogId);
        comment.setContent(content);
        comment.setAtId(atId);
        if (userId == blogerId) {
            // 如果是博主回复的
            notify.setRecevierId(atId);
            notifyMapper.insertSelective(notify);

            // 2.创建对应的评论消息
            comment.setNotifyId(notify.getId());
            commentMapper.insertSelective(comment);
        } else {
            // 如果是其他人回复其他人
            notify.setRecevierId(blogerId);
            // I.先给博主发送一份儿通知，创建相应评论消息
            notifyMapper.insertSelective(notify);
            comment.setNotifyId(notify.getId());
            commentMapper.insertSelective(comment);
            // II.再给被回复的人发一份儿通知，创建相应评论消息
            notify.setRecevierId(atId);
            notifyMapper.insertSelective(notify);
            comment.setNotifyId(notify.getId());
            commentMapper.insertSelective(comment);
        }   // end if:发送完了通知消息
    }

    @Override
    @Transactional// 开启事务
    public void deleteById(Long commentId) {
        Comment comment = commentMapper.selectByPrimaryKey(commentId);
        comment.setStatus(false);// 把状态置为false不可用
        commentMapper.updateByPrimaryKey(comment);
    }

    @Override
    @Transactional// 开启事务
    public List<CommentVo> listByBlogId(Long blogId) {
        List<CommentVo> resultList = new LinkedList<>();

        CommentExample commentExample = new CommentExample();
        commentExample.or().andBlogIdEqualTo(blogId);
        List<Comment> comments = commentMapper.selectByExample(commentExample);

        // 拼接数据
        resultList = joinCommentVo(comments);
        return resultList;
    }

    @Override
    @Transactional// 开启事务
    public List<CommentVo> listByUserId(Long userId, int pageNum, int pageSize) {
        List<CommentVo> resultList = new LinkedList<>();

        CommentExample commentExample = new CommentExample();
        commentExample.or().andUserIdEqualTo(userId);
        PageHelper.startPage(pageNum, pageSize);// 只对下一行的查询有效
        List<Comment> comments = commentMapper.selectByExample(commentExample);

        // 拼接数据
        resultList = joinCommentVo(comments);
        // 把所有的评论都置为已读
        Notify notify;
        for (Comment comment : comments) {
            notify = notifyMapper.selectByPrimaryKey(comment.getNotifyId());
            notify.setIsRead(true);
            notifyMapper.updateByPrimaryKey(notify);
        }   // end for

        return resultList;
    }

    /**
     * 通过传入的Comment集合拼接成需要的CommentVo数据返回
     *
     * @param comments
     * @return
     */
    private List<CommentVo> joinCommentVo(List<Comment> comments) {
        List<CommentVo> resultList = new LinkedList<>();

        // 拼接数据
        CommentVo commentVo;
        for (Comment comment : comments) {
            commentVo = new CommentVo();
            // 把Comment同CommentVo相同字段的数据赋值给CommentVo
            BeanUtils.copyProperties(comment, commentVo);
            // 拼接User数据
            User user = userMapper.selectByPrimaryKey(comment.getUserId());
            BeanUtils.copyProperties(user, commentVo);
            if (comment.getAtId() != null) {
                User atUser = userMapper.selectByPrimaryKey(comment.getAtId());
                commentVo.setAtUsername(atUser.getUsername());
            }
            // 拼接BlogInfo数据
            BlogInfo blogInfo = blogInfoMapper.selectByPrimaryKey(comment.getBlogId());
            commentVo.setBlogTitle(blogInfo.getTitle());

            resultList.add(commentVo);
        }   // end for

        return resultList;
    }

    @Override
    @Transactional// 开启事务
    public Long countByUserId(Long userId) {
        CommentExample commentExample = new CommentExample();
        commentExample.or().andUserIdEqualTo(userId);
        Long count = commentMapper.countByExample(commentExample);
        return count;
    }
}
