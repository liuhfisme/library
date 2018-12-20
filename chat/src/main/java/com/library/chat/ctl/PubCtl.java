package com.library.chat.ctl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: PubCtl
 * @Description: 控制器- 消息发布
 * @author feifei.liu
 * @date 2018/12/4 17:22
 */
@Controller
@RequestMapping("/pub")
public class PubCtl {
    @Autowired
    private SimpMessagingTemplate template;

    @RequestMapping("/index")
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("/pub/index");
        return mav;
    }

    @RequestMapping("/send")
    @ResponseBody
    public String send(String message){
//        template.convertAndSend("/topic/notice", "我是通知类消息："+message);
        template.convertAndSend("/queue/15311978870", "我是指定类消息："+message);
//        template.convertAndSendToUser("4028838765d0c6cd0165d0fdb8000002","/topic/chat", "我是指定消息");
        template.convertAndSendToUser("15311978870","/topic/chat", "我是指定类消息："+message);
        return HttpStatus.OK.toString();
    }

    /**
     * @Title: message
     * @Description: 测试消息
     * @Param [mav]
     * @return org.springframework.web.servlet.ModelAndView
     * @author feifei.liu
     * @date 2018/12/5 14:30
     */
    @RequestMapping("/message")
    public ModelAndView message(ModelAndView mav) {
        mav.setViewName("/pub/message");
        return mav;
    }
}
