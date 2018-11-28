package com.library.chat.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName: BaseMessage
 * @Description: TODO
 * @author feifei.liu
 * @date 2018/11/23 10:59
 */
@Data
@NoArgsConstructor
public class BaseMessage {
    private String type; //消息类型
    private String content; //消息内容
    private String sender; //发送者
    private String toType; //接收者 类型
    private String receiver; //接收者
    private Date date; //发送时间
}
