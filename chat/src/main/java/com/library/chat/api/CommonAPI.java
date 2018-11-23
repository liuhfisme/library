package com.library.chat.api;

import com.library.chat.config.security.UserPrincipal;
import com.library.chat.model.User;
import com.library.chat.service.RelationService;
import com.library.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Random;

/**
 * @ClassName: CommonAPI
 * @Description: TODO
 * @author feifei.liu
 * @date 2018/11/22 18:37
 */
@RestController
@RequestMapping("/api/common")
public class CommonAPI {
    @Autowired
    private UserService userService;

    @Autowired
    private RelationService relationService;

    @PostMapping(value = "/register")
    public boolean register(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        User user = new User(username, password, nickname, "/image/avatar/avatar" + new Random().nextInt(10) + ".jpg", new Date());
        return userService.addUser(user);
    }

    @PostMapping(value = "/add")
    public boolean add(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestParam String friend) {
        return  relationService.addFriend(userPrincipal.getUsername(), friend);
    }
}
