package com.mine.library.spring.webmvc;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * Created by feifei.liu on 2017/3/30.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebMvcConfig.class);

    }
}
