package com.library.spring.profile;

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;


/**
 * Created by feifei.liu on 2017/3/28.
 */
public class WebInit implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        container.setInitParameter("spring.profiles.default", "dev");
    }
}
