package com.library.spring.fortest;

/**
 * Created by liuff on 2017/3/29.
 */
public class TestBean {
    private String content;
    public  TestBean(String content) {
        super();
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
