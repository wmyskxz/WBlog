package wmyskxz.blog.web.service;

import wmyskxz.blog.module.vo.MessageListVo;
import wmyskxz.blog.module.vo.MessageVo;

import java.util.List;

/**
 * 私信消息相关Service
 *
 * @auth:wmyskxz
 * @date:2019/02/27 - 16:08
 */
public interface MessageService {

    /**
     * 发送私信
     *
     * @param userId
     * @param receiverId
     * @param content
     */
    void sendMessage(Long userId, Long receiverId, String content);

    /**
     * 删除私信列表
     *
     * @param userId
     * @param counterpartId
     */
    void deleteMessageListByUserIdAndCounterpartId(Long userId, Long counterpartId);

    /**
     * 删除用户所有私信
     *
     * @param userId
     */
    void deleteAllMessageListByUserId(Long userId);

    /**
     * 获取某一个用户的私信列表
     *
     * @param userId
     * @return
     */
    List<MessageListVo> getMessageListByUserId(Long userId);

    /**
     * 通过对方的id来获取详细的整个聊天内容
     *
     * @param userId
     * @param counterpartId
     * @return
     */
    public List<MessageVo> getMessagesByUserIdAndConterpartId(Long userId, Long counterpartId);
}
