package com.library.library.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by liuff on 2017/3/27.
 */
@Aspect //声明一个切面
@Component //注册成Spring容器管理的Bean
public class LogAspect {

    /**
     * 通过@Pointcut声明切点
     */
    @Pointcut("@annotation(com.library.library.spring.aop.Action)")
    public void annotationPointCut(){}

    /**
     *通过@After注解声明一个建言，并使用@Pointcut定义的切点
     * @param joinPoint
     */
    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);
        System.out.println("注解式拦截，"+action.name());
    }

    /**
     * 通过@Before注解声明一个建言，使用拦截规则作为参数
     * @param joinPoint
     */
    @Before("execution(* com.library.library.spring.aop.DemoMethodService.*(..))")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("方法规则拦截，"+method.getName());
    }
}
