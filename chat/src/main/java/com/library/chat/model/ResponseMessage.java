package com.library.chat.model;

/**
 * @ClassName: ResponseMessage
 * @Description: TODO
 * @author feifei.liu
 * @date 2018/11/15 15:39
 */
public class ResponseMessage {
    private String responseMessage;

    public ResponseMessage() {
    }

    public ResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
