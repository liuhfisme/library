package com.mine.library.spring.webmvc;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by liuff on 2017/3/31.
 */
@ControllerAdvice //声明控制器建言，组合了@Component注解，所以自动注册为Spring的Bean
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = Exception.class) //通过value属性可过滤拦截的条件
    public ModelAndView exception(Exception exception, WebRequest request) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", exception.getMessage());
        return modelAndView;
    }

    @ModelAttribute // 注解将键值对添加到全局，所有注解的@RequestMapping的方法可获得此键值对
    public void addAttributes(Model model) {
        model.addAttribute("msg", "额外信息");
    }

    @InitBinder //定制WebDataBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id"); //演示：忽略request参数的id
    }
}
