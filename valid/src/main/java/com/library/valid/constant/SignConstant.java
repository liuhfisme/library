package com.library.valid.constant;

/**
 * 签名认证常量类.
 *
 * @author liufeifei02@beyondsoft.com
 * @version 1.0
 * @date 2019-03-05
 */
public interface SignConstant {
    /**
     * 签名超时时长，默认时间为5分钟，ms
     */
    long EXPIRE_TIME = 5 * 60 * 1000L;
    String TIMESTAMP_KEY = "timestamp";
    String SIGN_KEY = "sign";
    String SECRET_KEY = "secretKey";
}