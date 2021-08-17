package com.library.utils.design.chainOfResponsibility;

import com.library.utils.design.chainOfResponsibility.chain.DemoHandlerChain;

/**
 * .
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2021-08-17
 */
public class DemoDefine {
    public static void main(String[] args) {
        // 初始上下文
        DemoContext.NAMES.set("Hello");
        // 执行handler
        DemoHandlerChain.handlers.forEach(handler -> handler.execute());
        // 销毁上下文
        DemoContext.cleanup();
    }
}