package com.library.spring.webmvc;

import com.library.spring.webmvc.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liuff on 2017/3/31.
 */
@Controller
public class AdviceController {

    @RequestMapping(value = "/advice")
    public String getSomething(@ModelAttribute("msg") String msg, DemoObj obj) {
        throw new IllegalArgumentException("非常抱歉，参数错误/"+"来自@ModelAttribute："+msg);
    }
}
