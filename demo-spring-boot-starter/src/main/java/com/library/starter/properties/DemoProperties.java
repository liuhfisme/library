package com.library.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * .
 *
 * @date 2020-09-11
 * @version 1.0
 * @author liufefei02@beyondsoft.com
 */
@ConfigurationProperties(prefix = "demo")
public class DemoProperties {
    private String ip;
    private String host;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}