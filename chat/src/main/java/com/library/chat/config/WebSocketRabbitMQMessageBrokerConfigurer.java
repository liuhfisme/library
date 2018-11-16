package com.library.chat.config;

import com.library.chat.aop.LibraryChannelInterceptorAdapter;
import com.library.chat.aop.LibraryHandShakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * @ClassName: WebSocketRabbitMQMessageBrokerConfigurer
 * @Description: TODO
 * @author feifei.liu
 * @date 2018/11/16 10:57
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketRabbitMQMessageBrokerConfigurer extends AbstractWebSocketMessageBrokerConfigurer { //// 此注解开使用STOMP协议来传输基于消息代理的消息，此时可以在@Controller类中使用@MessageMapping
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
//        registry.addEndpoint("/websocket-rabbitmq").withSockJS();
        registry.addEndpoint("/websocket-simple")
                .setAllowedOrigins("*") //添加允许跨域访问
                .addInterceptors(handShakeInterceptor) //添加自定义拦截
                .withSockJS();
        registry.addEndpoint("/websocket-simple-single")
                .setAllowedOrigins("*") //添加允许跨域访问
                .addInterceptors(handShakeInterceptor) //添加自定义拦截
                .withSockJS();
        registry.addEndpoint("//websocket-simple-rabbitmq")
                .setAllowedOrigins("*") //添加允许跨域访问
                .addInterceptors(handShakeInterceptor) //添加自定义拦截
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /*
         * 配置消息代理
         * 使用RabbitMQ做为消息代理，替换默认的Simple Broker
         */
        registry.enableStompBrokerRelay("/exchange","/topic","/queue","/amq/queue","reply-queue") // "STOMP broker relay"处理所有消息将消息发送到外部的消息代理
                .setRelayHost("192.168.20.201")
                .setClientLogin("admin").setClientPasscode("admin")
                .setSystemLogin("admin").setSystemPasscode("admin")
                .setSystemHeartbeatSendInterval(5000)
                .setSystemHeartbeatReceiveInterval(4000);
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        ChannelRegistration channelRegistration = registration.setInterceptors(channelInterceptorAdapter);
        super.configureClientInboundChannel(registration);
    }
}