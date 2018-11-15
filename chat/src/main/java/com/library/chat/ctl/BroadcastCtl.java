package com.library.chat.ctl;

import com.alibaba.fastjson.JSONObject;
import com.library.chat.model.RequestMessage;
import com.library.chat.model.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @ClassName: BroadcastCtl
 * @Description: TODO
 * @author feifei.liu
 * @date 2018/11/15 15:40
 */
@Controller
public class BroadcastCtl {
    private static final Logger logger = LoggerFactory.getLogger(BroadcastCtl.class);

    //收到消息计数
    private AtomicInteger count = new AtomicInteger(0);

    /**
     * @Title: boradcast
     * @Description:
     *  @MessageMapping 指定要接收消息的地址，类似@RequestMapping。除了注解到方法上，也可以注解到类上
     *  @SendTo默认 消息将被发送到与传入消息相同的目的地
     *  消息的返回值是通过{@link org.springframework.messaging.converter.MessageConverter}进行转换
     * @Param [requestMessage]
     * @return com.library.chat.model.ResponseMessage
     * @author feifei.liu
     * @date 2018/11/15 15:55
     */
    @MessageMapping("/receive")
    @SendTo("/topic/getResponse")
    public ResponseMessage boradcast(RequestMessage requestMessage) {
        logger.info("receive message = {}", JSONObject.toJSONString(requestMessage));
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setResponseMessage("BroadcastCtl receive ["+count.incrementAndGet()+"] records");
        return responseMessage;
    }

    @RequestMapping(value = "/broadcast/index")
    public ModelAndView broadcastIndex(ModelAndView mav, HttpServletRequest req) {
        logger.info("remoteHost："+req.getRemoteHost());
        mav.setViewName("/websocket/simple/ws-broadcast");
        return mav;
    }
}
