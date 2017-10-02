package com.library.library.sboot.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by liuff on 2017/4/6.
 */
@Controller
public class WsController {
    @RequestMapping("/ws")
    public ModelAndView ws(ModelAndView mav) {
        mav.setViewName("/ws");
        return mav;
    }

    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public LibraryResponse say(LibraryMessage message) throws Exception {
        Thread.sleep(3000);
        return  new LibraryResponse("Welcome, "+message.getName()+"!");
    }
}
