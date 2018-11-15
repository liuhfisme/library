package com.library.sboot.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SendController
 * @Description: TODO
 * @author feifei.liu
 * @date 2018/11/15 11:32
 */
@RestController
public class SendController {

    @Autowired
    private Sender sender;

    @RequestMapping(value = "/send")
    public String send(String msg) {
        sender.send(msg);
        return "发送成功："+msg;
    }
}
