package wmyskxz.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wmyskxz.blog.module.vo.MessageListVo;
import wmyskxz.blog.web.service.MessageService;

import javax.annotation.Resource;
import java.util.List;

/**
 * MessageService功能测试类
 *
 * @auth:wmyskxz
 * @date:2019/02/27 - 21:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceTester {
    @Resource
    MessageService messageService;

    @Test
    public void getMessageListByUserIdTester() {
        List<MessageListVo> messageListVos = messageService.listByUserId(1L);
        for (MessageListVo messageListVo : messageListVos) {
            System.out.println("Avatar:" + messageListVo.getAvatar());
            System.out.println("CounterpartId:" + messageListVo.getCounterpartId());
            System.out.println("LastChatContent:" + messageListVo.getLastChatContent());
            System.out.println("LastChatTime:" + messageListVo.getLastChatTime());
        }
    }
}
