package com.library.chat.ctl;

import com.library.chat.config.security.UserPrincipal;
import com.library.chat.model.User;
import com.library.chat.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @ClassName: PageCtl
 * @Description: TODO
 * @author feifei.liu
 * @date 2018/11/23 10:50
 */
@Controller
public class PageCtl {

    @Autowired
    private RelationService relationService;

    @GetMapping(value = "/")
    public String index() {
//        return "/chat/index";
        return "/chat/login";
    }

    @GetMapping(value = "/chat")
    public String chat(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        model.addAttribute("user", userPrincipal);
        String username = userPrincipal.getUsername();
        List<User> friends = relationService.listFriends(username);
        model.addAttribute("friends", friends);
        return "/chat/chat";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "/chat/login";
    }

    @GetMapping(value = "/register")
    public String register() {
        return "/chat/register";
    }
}
