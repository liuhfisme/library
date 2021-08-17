package com.library.utils.design.chainOfResponsibility.handler;

import com.library.utils.design.chainOfResponsibility.DemoContext;

/**
 * .
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2021-08-17
 */
public class DemoFirstHandler extends DemoAbstractHandler {
    @Override
    public void execute() {
        // 从上下文中获取值
        String s = DemoContext.NAMES.get();
        s += " >>> DemoFirstHandler";
        DemoContext.NAMES.set(s);
        System.out.println(s);
    }
}