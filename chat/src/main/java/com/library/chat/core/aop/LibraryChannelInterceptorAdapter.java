package com.library.chat.core.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.stereotype.Component;

/**
 * @ClassName: LibraryChannelInterceptorAdapter
 * @Description: 拦截Message。可以在Message对被在发送到MessageChannel前后查看修改此值，也可以在MessageChannel接收MessageChannel对象前后修改此值
 * @author feifei.liu
 * @date 2018/11/15 16:46
 */
@Component
public class LibraryChannelInterceptorAdapter extends ChannelInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(LibraryChannelInterceptorAdapter.class);

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public boolean preReceive(MessageChannel channel) {
        logger.info(this.getClass().getCanonicalName()+" preReceive");
        return super.preReceive(channel);
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        logger.info(this.getClass().getCanonicalName()+" preSend");
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();
        //检测用户订阅内容（防止用户订阅不合法频道）
        if (StompCommand.SUBSCRIBE.equals(command)) {
            logger.info(this.getClass().getCanonicalName()+" 用户订阅目的地="+accessor.getDestination());
            // 如果该用户订阅的频道不合法直接返回null前端用户就接受不到该频道信息
            return super.preSend(message, channel);
        }
        return super.preSend(message, channel);
    }

    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        logger.info(this.getClass().getCanonicalName()+" afterSendCompletion");
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();
        if (StompCommand.SUBSCRIBE.equals(command)) {
            logger.info(this.getClass().getCanonicalName()+" 订阅消息发送成功");
            this.simpMessagingTemplate.convertAndSend("/topic/getResponse", "{'msg':'订阅成功'}");
        }
        //如果用户断开连接
        if (StompCommand.DISCONNECT.equals(command)) {
            logger.info(this.getClass().getCanonicalName()+" 用户断开连接成功");
            simpMessagingTemplate.convertAndSend("/topic/getResponse", "{'msg':'用户断开连接成功'}");
        }
        super.afterSendCompletion(message, channel, sent, ex);
    }
}
