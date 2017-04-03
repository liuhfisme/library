package com.mine.library.spring.webmvc;

import com.mine.library.spring.webmvc.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuff on 2017/4/3.
 */
@RestController
public class NormalRestController {

    @Autowired
    DemoService demoService;

    @RequestMapping(value = "/testRest", produces = "text/plain; charset=UTF-8")
    public @ResponseBody String testRest() {
        return demoService.saySomething();
    }
}
