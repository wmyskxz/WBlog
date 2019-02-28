package wmyskxz.blog.web.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import wmyskxz.blog.module.dao.BlogInfoMapper;
import wmyskxz.blog.module.dao.CommentMapper;
import wmyskxz.blog.module.dao.NotifyMapper;
import wmyskxz.blog.module.dao.UserMapper;
import wmyskxz.blog.module.entity.Comment;
import wmyskxz.blog.module.entity.CommentExample;
import wmyskxz.blog.module.entity.Notify;
import wmyskxz.blog.module.entity.User;
import wmyskxz.blog.module.vo.CommentVo;
import wmyskxz.blog.util.ConstCode;
import wmyskxz.blog.web.service.CommentService;

import java.util.LinkedList;
import java.util.List;

/**
 * CommentService实现类
 *
 * @auth:wmyskxz
 * @date:2019/02/27 - 15:28
 */
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    BlogInfoMapper blogInfoMapper;
    @Autowired
    NotifyMapper notifyMapper;

    @Override
    @Transactional// 开启事务
    public void addComment(Long userId, Long blogId, String content, Long atId) {

        // 1.先要创建对应的通知消息
        Notify notify = new Notify();
        notify.setSenderId(userId);
        notify.setRecevierId(blogInfoMapper.selectByPrimaryKey(blogId).getUserId());
        notify.setType(ConstCode.NOTIFY_COMMENT_TYPE);
        Long notifyId = Long.valueOf(notifyMapper.insertSelective(notify));

        // 2.创建对应的评论消息
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setBlogId(blogId);
        comment.setContent(content);
        comment.setAtId(atId);
        comment.setNotifyId(notifyId);
        commentMapper.insertSelective(comment);
    }

    @Override
    @Transactional// 开启事务
    public void deleteCommentByCommentId(Long commentId) {
        Comment comment = commentMapper.selectByPrimaryKey(commentId);
        comment.setStatus(false);// 把状态置为false不可用
        commentMapper.updateByPrimaryKey(comment);
    }

    @Override
    @Transactional// 开启事务
    public List<CommentVo> getCommentsByBlogId(Long blogId) {
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
    public List<CommentVo> getCommentsByUserId(Long userId) {
        List<CommentVo> resultList = new LinkedList<>();

        CommentExample commentExample = new CommentExample();
        commentExample.or().andUserIdEqualTo(userId);
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
        CommentVo commentVo = new CommentVo();
        for (Comment comment : comments) {
            // 把Comment同CommentVo相同字段的数据赋值给CommentVo
            BeanUtils.copyProperties(comment, commentVo);
            // 拼接User数据
            User user = userMapper.selectByPrimaryKey(comment.getUserId());
            BeanUtils.copyProperties(user, commentVo);

            resultList.add(commentVo);
        }   // end for

        return resultList;
    }
}
