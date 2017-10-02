package com.library.library.spring.el;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by liuff on 2017/3/27.
 */
@Service
public class DemoService {
    @Value("其他类的属性") //注入普通字符
    private String another;

    public String getAnother() {
        return another;
    }

    public void setAnother(String another) {
        this.another = another;
    }
}
