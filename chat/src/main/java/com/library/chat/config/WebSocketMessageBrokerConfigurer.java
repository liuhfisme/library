package com.library.chat.config;

import com.library.chat.aop.LibraryChannelInterceptorAdapter;
import com.library.chat.aop.LibraryHandShakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * @ClassName: WebSocketMessageBrokerConfigurer
 * @Description: WebSocket配置
 * @author feifei.liu
 * @date 2018/11/15 15:59
 */
@Configuration
@EnableWebSocketMessageBroker // 此注解表示使用STOMP协议来传输基于消息代理的消息，此时可以在@Controller类中使用@MessageMapping
public class WebSocketMessageBrokerConfigurer extends AbstractWebSocketMessageBrokerConfigurer {

    @Autowired
    private LibraryHandShakeInterceptor handShakeInterceptor;

    @Autowired
    private LibraryChannelInterceptorAdapter channelInterceptorAdapter;

    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /*
         * 注册 Stomp的端点
         * addEndpoint：添加STOMP协议的端点。这个HTTP URL是供WebSocket或SockJS客户端访问的地址
         * withSockJS：指定端点使用SockJS协议
         */
        registry.addEndpoint("/websocket-simple")
                .setAllowedOrigins("*") //添加允许跨域访问
                .addInterceptors(handShakeInterceptor) //添加自定义拦截
                .withSockJS();
        registry.addEndpoint("/websocket-simple-single")
                .setAllowedOrigins("*") //添加允许跨域访问
                .addInterceptors(handShakeInterceptor) //添加自定义拦截
                .withSockJS();
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /*
         * 配置消息代理
         * 启动简单Broker，消息的发送的地址符合配置的前缀来的消息才发送到这个broker
         */
        registry.enableSimpleBroker("/topic","/queue");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        ChannelRegistration channelRegistration = registration.setInterceptors(channelInterceptorAdapter);
        super.configureClientInboundChannel(registration);
    }
}
