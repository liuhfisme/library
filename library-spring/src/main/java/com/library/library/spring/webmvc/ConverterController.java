package com.library.library.spring.webmvc;

import com.library.library.spring.webmvc.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liuff on 2017/4/2.
 */
@Controller
public class ConverterController {

    @RequestMapping(value = "/convert", produces = {"application/x-wisely"})
    public @ResponseBody DemoObj convert(@RequestBody DemoObj demoObj) {
        return  demoObj;
    }
}
