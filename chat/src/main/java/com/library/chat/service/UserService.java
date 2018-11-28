package com.library.chat.service;

import com.library.chat.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: UserService
 * @Description: 业务实现- 用户
 * @author feifei.liu
 * @date 2018/11/22 18:20
 */
@Service
public class UserService {
    //ConcurrentHashMap支持并发操作
    private final ConcurrentHashMap<String, User> users;

    public UserService() {
        users = new ConcurrentHashMap<String, User>();
    }

    public boolean addUser(User user) {
        boolean isExist = users.containsKey(user.getUsername());
        if (isExist) {
            return false;
        }
        users.put(user.getUsername(), user);
        return true;
    }

    public User getByUsername(String username) {
        return users.get(username);
    }
}
