package com.library.library.sboot.websocket;

/**
 * Created by liuff on 2017/4/6.
 */
public class LibraryResponse {
    private String responseMessage;
    public LibraryResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
