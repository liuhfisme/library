package com.library.chat.model;

/**
 * @ClassName: RequestMessage
 * @Description: TODO
 * @author feifei.liu
 * @date 2018/11/15 15:38
 */
public class RequestMessage {
    private String name;

    public RequestMessage() {
    }

    public RequestMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
