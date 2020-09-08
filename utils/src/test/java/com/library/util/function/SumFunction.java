package com.library.util.function;

import com.googlecode.aviator.runtime.function.AbstractVariadicFunction;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Map;

/**
 * .
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2020-05-29
 */
public  class SumFunction extends AbstractVariadicFunction {

    @Override
    public String getName() {
        return "sum";
    }


    @Override
    public AviatorObject variadicCall(Map<String, Object> map, AviatorObject... aviatorObjects) {
        return null;
    }
}