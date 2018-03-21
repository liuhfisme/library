//package com.mine.library.sboot.javaconfig;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.web.javaconfig.EnableSpringDataWebSupport;
//import org.springframework.web.servlet.javaconfig.annotation.EnableWebMvc;
//import org.springframework.web.servlet.javaconfig.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.javaconfig.annotation.WebMvcConfigurerAdapter;
//
///**
// * Created by liuff on 2017/4/7.
// */
//@Configuration
//@EnableWebMvc
//@EnableSpringDataWebSupport
//public class WebMvcConfig extends WebMvcConfigurerAdapter{
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        super.addViewControllers(registry);
//        registry.addViewController("/ws").setViewName("/ws");
//    }
//
////    @Override
////    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        registry.addResourceHandler("/static/**","/views/**")
////                .addResourceLocations("/static/","/views/");
////    }
//}
