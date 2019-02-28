package wmyskxz.blog.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wmyskxz.blog.module.dao.MessageMapper;
import wmyskxz.blog.module.dao.NotifyMapper;
import wmyskxz.blog.module.dao.UserMapper;
import wmyskxz.blog.module.entity.Message;
import wmyskxz.blog.module.entity.MessageExample;
import wmyskxz.blog.module.entity.Notify;
import wmyskxz.blog.module.entity.User;
import wmyskxz.blog.module.vo.MessageListVo;
import wmyskxz.blog.module.vo.MessageVo;
import wmyskxz.blog.util.ConstCode;
import wmyskxz.blog.web.service.MessageService;

import javax.annotation.Resource;
import java.util.*;

/**
 * MessageService实现类
 *
 * @auth:wmyskxz
 * @date:2019/02/27 - 19:33
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    MessageMapper messageMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    NotifyMapper notifyMapper;

    @Override
    @Transactional// 开启事务
    public void sendMessage(Long userId, Long receiverId, String content) {
        // 1.先创建对应的notify通知
        Notify notify = new Notify();
        notify.setType(ConstCode.NOTIFY_MESSAGE_TYPE);
        notify.setSenderId(userId);
        notify.setRecevierId(receiverId);
        Long notifyId = Long.valueOf(notifyMapper.insertSelective(notify));

        // 2.再创建对应的Message私信消息
        Message message = new Message();
        message.setContent(content);
        message.setNotifyId(notifyId);
        message.setSenderId(userId);
        message.setRecevierId(receiverId);
        messageMapper.insertSelective(message);
    }

    @Override
    @Transactional// 开启事务
    public void deleteMessageListByUserIdAndCounterpartId(Long userId, Long counterpartId) {
        // 1.删除所有以该用户为sender的私信数据(置is_delete_by_sender为true)
        Message message = new Message();
        message.setIsDeleteBySender(true);
        MessageExample messageExample = new MessageExample();
        messageExample.or().andSenderIdEqualTo(userId).andRecevierIdEqualTo(counterpartId);
        messageMapper.updateByExampleSelective(message, messageExample);

        // 2.同样删除所有以该用户为receiver的私信数据(置is_delete_by_receiver为true)
        message = new Message();
        message.setIsDeleteByRecevier(true);
        messageExample = new MessageExample();
        messageExample.or().andSenderIdEqualTo(counterpartId).andRecevierIdEqualTo(userId);
        messageMapper.updateByExampleSelective(message, messageExample);
    }

    @Override
    @Transactional// 开启事务
    public void deleteAllMessageListByUserId(Long userId) {
        // 1.先删除所有以该用户为sender的私信数据(置is_delete_by_sender为true)
        Message message = new Message();
        message.setIsDeleteBySender(true);
        MessageExample messageExample = new MessageExample();
        messageExample.or().andSenderIdEqualTo(userId);
        messageMapper.updateByExampleSelective(message, messageExample);

        // 2.再删除所有以该用户为recevier的私信数据(置is_delete_by_recevier为true)
        message = new Message();
        message.setIsDeleteByRecevier(true);
        messageExample = new MessageExample();
        messageExample.or().andRecevierIdEqualTo(userId);
        messageMapper.updateByExampleSelective(message, messageExample);
    }

    @Override
    @Transactional// 开启事务
    public List<MessageListVo> getMessageListByUserId(Long userId) {

        List<MessageListVo> resultList = new LinkedList<>();

        // 1.首先查询一共有多少个私信列表(要本身没有删除的)
        MessageExample messageExample = new MessageExample();
        messageExample.or().andSenderIdEqualTo(userId).andIsDeleteBySenderEqualTo(false);
        List<Message> senderList = messageMapper.selectByExample(messageExample);

        messageExample = new MessageExample();
        messageExample.or().andRecevierIdEqualTo(userId).andIsDeleteByRecevierEqualTo(false);
        List<Message> recevierList = messageMapper.selectByExample(messageExample);

        // 创建一个Map<对方ID,userId>来过滤信息
        Map<Long, Long> messageListMap = new HashMap<>();
        for (Message message : senderList) {
            messageListMap.put(message.getRecevierId(), message.getSenderId());
        }   // end for
        for (Message message : recevierList) {
            messageListMap.put(message.getSenderId(), message.getRecevierId());
        }   // end for

        // 2.根据messageListMap中的对方id查询相关数据
        MessageListVo messageListVo;
        for (Long counterpartId : messageListMap.keySet()) {
            messageListVo = new MessageListVo();
            // I.查询用户的相关信息
            User user = userMapper.selectByPrimaryKey(counterpartId);
            messageListVo.setAvatar(user.getAvatar());
            messageListVo.setUsername(user.getUsername());
            messageListVo.setCounterpartId(counterpartId);
            // II.查询最后一次聊天的时间和内容
            messageExample = new MessageExample();
            messageExample.setOrderByClause("create_time DESC");
            List idList = Arrays.asList(new Long[]{counterpartId, userId});
            messageExample.or().andRecevierIdIn(idList).andSenderIdIn(idList);
            Message lastChatMessage = messageMapper.selectByExample(messageExample).get(0);
            messageListVo.setLastChatContent(lastChatMessage.getContent());
            messageListVo.setLastChatTime(lastChatMessage.getCreateTime());

            resultList.add(messageListVo);
        }   // end for:查询拼接数据完成并添加进了resultList

        return resultList;
    }

    @Override
    @Transactional// 开启事务
    public List<MessageVo> getMessagesByUserIdAndConterpartId(Long userId, Long counterpartId) {

        List<MessageVo> resultList = new LinkedList<>();

        MessageExample messageExample = new MessageExample();
        List idList = Arrays.asList(new Long[]{counterpartId, userId});
        messageExample.or().andSenderIdIn(idList).andRecevierIdIn(idList);
        List<Message> messages = messageMapper.selectByExample(messageExample);

        User user = userMapper.selectByPrimaryKey(userId);
        User counterpartUser = userMapper.selectByPrimaryKey(counterpartId);

        // 拼接数据
        MessageVo messageVo;
        for (Message message : messages) {
            messageVo = new MessageVo();
            messageVo.setContent(message.getContent());
            messageVo.setCreateTime(message.getCreateTime());
            messageVo.setUserId(message.getSenderId() == userId ? userId : counterpartId);
            messageVo.setAvatar(message.getSenderId() == userId ? user.getAvatar() : counterpartUser.getAvatar());

            resultList.add(messageVo);
        }   // end for

        return resultList;
    }
}
