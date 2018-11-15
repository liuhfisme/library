package com.library.chat.ctl;

import com.alibaba.fastjson.JSONObject;
import com.library.chat.model.RequestMessage;
import com.library.chat.model.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: BroadcastSingleCtl
 * @Description: TODO
 * @author feifei.liu
 * @date 2018/11/15 17:10
 */
@Controller
public class BroadcastSingleCtl {
    private static final Logger logger = LoggerFactory.getLogger(BroadcastSingleCtl.class);

    //收到消息计数
    private AtomicInteger count = new AtomicInteger(0);

    @MessageMapping("/receive-single")
    @SendToUser("/topic/getResponse")
    public ResponseMessage boradcast(RequestMessage requestMessage) {
        logger.info("receive message = {}", JSONObject.toJSONString(requestMessage));
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setResponseMessage("BroadcastCtl receive ["+count.incrementAndGet()+"] records");
        return responseMessage;
    }

    @RequestMapping(value = "/broadcast-single/index")
    public ModelAndView broadcastIndex(ModelAndView mav, HttpServletRequest req) {
        logger.info("remoteHost："+req.getRemoteHost());
        mav.setViewName("/websocket/simple/ws-broadcast-single");
        return mav;
    }

}
