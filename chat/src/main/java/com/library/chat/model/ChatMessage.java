package com.library.chat.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: ChatMessage
 * @Description: TODO
 * @author feifei.liu
 * @date 2018/11/23 11:day01
 */
@Data
@NoArgsConstructor
public class ChatMessage {
    private String username;
    private String nickname;
    private String avatar;
    private String content;
    private String sendTime;
}
