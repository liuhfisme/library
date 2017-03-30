package com.mine.library.spring.webmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by feifei.liu on 2017/3/30.
 */
@Controller
public class DemoController {
    @RequestMapping("/index")
    public String demo() {
        return "index";
    }
}
