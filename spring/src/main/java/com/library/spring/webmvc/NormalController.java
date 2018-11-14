package com.library.spring.webmvc;

import com.library.spring.webmvc.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liuff on 2017/4/3.
 */
@Controller
public class NormalController {

    @Autowired
    DemoService demoService;

    @RequestMapping("/normal")
    public String testPage(Model model) {
        model.addAttribute("msg", demoService.saySomething());
        return "page";
    }
}
