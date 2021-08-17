package com.library.utils.design.chainOfResponsibility;

/**
 * 上下文类.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2021-08-17
 */
public class DemoContext {

    public static final ThreadLocal<String> NAMES = new ThreadLocal<>();

    public static void cleanup() {
        NAMES.remove();
    }
}