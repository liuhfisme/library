package com.library.starter.service;
/**
 * .
 *
 * @date 2020-09-11
 * @version 1.0
 * @author liufefei02@beyondsoft.com
 */
public class DemoService {
    public String ip;
    public String host;
    public DemoService(String ip, String host) {
        this.ip = ip;
        this.host = host;
    }

    public String link() {
        return this.ip+":"+this.host;
    }
}