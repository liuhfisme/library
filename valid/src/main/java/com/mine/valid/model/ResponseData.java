/*
 * Copyright (c) 2018. Beyondsoft Corporation. All Rights Reserved.
 *
 * BEYONDSOFT CONFIDENTIALITY
 *
 * The information in this file is confidential and privileged from Beyondsoft Corporation, which is intended only for
 * use solely by Beyondsoft Corporation. Disclosure, copying, distribution, or use of the contents of this file by
 * persons other than Beyondsoft Corporation is strictly prohibited and may violate applicable laws.
 */

package com.mine.valid.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 通用Response数据返回值的包装类.
 *
 * @date: 2018-10-18
 * @version: 1.0
 * @author: robin.luo@beyondsoft.com
 */

public class ResponseData<T> implements Serializable {

    private static final long serialVersionUID = 3641988897219827606L;
    private int code = 200;
    private String messageKey = "";
    private String messageInfo = "";
    private T data;

    public ResponseData() {
    }
    private ResponseData(T data) {
        this.data = data;
    }

    private ResponseData(int code, String messageKey, String messageInfo) {
        this.code = code;
        this.messageKey = messageKey;
        this.messageInfo = messageInfo;
    }

    public static ResponseData instance(Object data) {
        return new ResponseData(data);
    }

    public static ResponseData instance(int code, String message, String errorInfo) {
        ResponseData<ArrayList> responseData = new ResponseData(code, message, errorInfo);
        responseData.setData(new ArrayList());
        return responseData;
    }

    public static <T> ResponseData<T> instance(int code, String message, String errorInfo,T data) {
        ResponseData<T> responseData = new ResponseData<T>(code, message, errorInfo);
        responseData.setData(data);
        return responseData;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(String messageInfo) {
        this.messageInfo = messageInfo;
    }

}
