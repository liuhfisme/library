package com.library.utils.design.chainOfResponsibility.chain;


import com.library.utils.design.chainOfResponsibility.handler.DemoAbstractHandler;
import com.library.utils.design.chainOfResponsibility.handler.DemoFirstHandler;
import com.library.utils.design.chainOfResponsibility.handler.DemoSecondHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2021-08-17
 */
public class DemoHandlerChain {
    public final static List<DemoAbstractHandler> handlers = new ArrayList<>();
    static {
        handlers.add(new DemoFirstHandler());
        handlers.add(new DemoSecondHandler());
    }
}