package com.mine.library.spring.webmvc;

import com.mine.library.spring.webmvc.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created by liuff on 2017/4/2.
 */
@Controller
public class AsyncController {

    @Autowired
    PushService pushService;

    @RequestMapping(value = "/defer")
    public @ResponseBody DeferredResult<String> deferredCall() {
        return  pushService.getAsynvUpdate();
    }
}
