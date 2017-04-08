package com.mine.library.sboot.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by liuff on 2017/4/6.
 */
@Controller
public class WsController {

    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public LibraryResponse say(LibraryMessage message) throws Exception {
        Thread.sleep(3000);
        return  new LibraryResponse("Welcome, "+message.getName()+"!");
    }
}
