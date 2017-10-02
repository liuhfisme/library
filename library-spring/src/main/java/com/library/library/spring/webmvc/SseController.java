package com.library.library.spring.webmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * Created by liuff on 2017/4/2.
 */
@Controller
public class SseController {

    @RequestMapping(value = "/push", produces = "text/event-stream; charset=UTF-8")
    public @ResponseBody String push() {
        Random random = new Random();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "data:Testing 1,2,3"+random.nextInt()+"\n\n";
    }
}
