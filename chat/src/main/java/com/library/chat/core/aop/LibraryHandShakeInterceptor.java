package com.library.chat.core.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * @ClassName: LibraryHandShkeInceptor
 * @Description: 拦截websocket的握手请求。在服务端和客户端在进行握手时会被执行
 * @author feifei.liu
 * @date 2018/11/15 16:42
 */
@Component
public class LibraryHandShakeInterceptor implements HandshakeInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LibraryHandShakeInterceptor.class);

    /**
     * @Title: beforeHandshake
     * @Description: 握手前
     * @Param [request, response, wsHandler, attributes]
     * @return boolean
     * @author feifei.liu
     * @date 2018/11/15 16:45
     */
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        logger.info(this.getClass().getCanonicalName()+" http协议转换websoket协议进行前，握手前"+request.getURI());
        return true;
    }

    /**
     * @Title: afterHandshake
     * @Description: 握手成功后
     * @Param [request, response, wsHandler, ex]
     * @return void
     * @author feifei.liu
     * @date 2018/11/15 16:45
     */
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        logger.info(this.getClass().getCanonicalName()+" 握手成功后……");
    }
}
