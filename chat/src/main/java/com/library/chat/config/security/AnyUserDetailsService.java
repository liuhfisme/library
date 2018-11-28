package com.library.chat.config.security;

import com.library.chat.model.User;
import com.library.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: AnyUserDetailsService
 * @Description: TODO
 * @author feifei.liu
 * @date 2018/11/22 18:53
 */
@Service
public class AnyUserDetailsService implements UserDetailsService {
    private final static String ROLE_TAG = "ROLE_USER";

    @Autowired
    private UserService userService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(ROLE_TAG));
        //用户认证（用户名、密码、权限）
        UserPrincipal userPrincipal = new UserPrincipal(username, user.getPassword(), authorities);
        userPrincipal.setNickname(user.getNickname());
        userPrincipal.setAvatar(user.getAvatar());
        return userPrincipal;
    }
}
