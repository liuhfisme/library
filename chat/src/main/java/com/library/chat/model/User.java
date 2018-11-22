package com.library.chat.model;

import lombok.*;

import java.util.Date;

/**
 * @ClassName: User
 * @Description: TODO
 * @author feifei.liu
 * @date 2018/11/22 17:50
 */
@Data
@NoArgsConstructor
@ToString
public class User {
    private String username; //用户名
    private String password; //密码
    private String nickname; //昵称
    private String avatar; //头像
    private Date joinTime; //登录时间

    public User(String username, String password, String nickname, String avatar, Date joinTime) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.avatar = avatar;
        this.joinTime = joinTime;
    }

}
