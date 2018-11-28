package com.library.chat.service;

import com.library.chat.model.User;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: RelationService
 * @Description: 社交功能
 * @author feifei.liu
 * @date 2018/11/22 18:27
 */
@Service
@Log4j
public class RelationService {
    private final ConcurrentHashMap<String, List<String>> relations;

    public RelationService() {
        relations = new ConcurrentHashMap<String, List<String>>();
    }

    @Autowired
    private UserService userService;

    /**
     * @Title: addFriend
     * @Description: 添加好友
     * @Param [username, friendName]
     * @return boolean
     * @author feifei.liu
     * @date 2018/11/22 18:33
     */
    public boolean addFriend(String username, String friendName) {
        User user = userService.getByUsername(friendName);
        if (user == null) {
            log.info("用户不存在："+friendName);
            return false;
        }
        if (username.equals(friendName)) {
            log.info("不能添加自己为好友："+friendName);
            return false;
        }
        this.establishRelation(username, friendName);
        this.establishRelation(friendName, username);
        return true;
    }

    /**
     * @Title: establishRelation
     * @Description: 好友添加方法（内部）
     * @Param [username, friendName]
     * @return void
     * @author feifei.liu
     * @date 2018/11/22 18:32
     */
    private void establishRelation(String username, String friendName) {
        List<String> friends = relations.get(username);
        if (friends == null) {
            friends = new ArrayList<String>();
        }
        friends.add(friendName);
        relations.put(username, friends);
    }

    /**
     * @Title: listFriends
     * @Description: 好友列表
     * @Param [username]
     * @return java.util.List<com.library.chat.model.User>
     * @author feifei.liu
     * @date 2018/11/22 18:35
     */
    public List<User> listFriends(String username) {
        List<User> users = new ArrayList<User>();
        List<String> friends = relations.get(username);
        if (friends != null) {
            for (String friendName: friends) {
                User user = userService.getByUsername(friendName);
                users.add(user);
            }
        }
        return users;
    }
}
