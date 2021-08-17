package com.library.util.function;

import com.googlecode.aviator.runtime.function.AbstractVariadicFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDecimal;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 浮点数舍入函数.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2021-08-16
 */
public  class ScaleFunction extends AbstractVariadicFunction {

    /**
     * 常量
     */
    private final static String FUNC_NAME = "scale";
    private final static int PAR_LEN2 = 2;
    private final static int PAR_LEN3 = 3;

    @Override
    public String getName() {
        return FUNC_NAME;
    }

    @Override
    public AviatorObject variadicCall(Map<String, Object> env, AviatorObject... aviatorObjects) {
        // 校验：参数长度
        int length = aviatorObjects.length;
        if (length < PAR_LEN2 || length > PAR_LEN3) {
            throw new IllegalArgumentException("args length not support!");
        }
        // 校验：空值
        int nilSize = Arrays.stream(aviatorObjects).filter(item -> item.isNull(env)).collect(Collectors.toList()).size();
        if (nilSize > 0) {
            throw new IllegalArgumentException("it looks some args is null!");
        }

        // 取值：获取参数值
        Number number = FunctionUtils.getNumberValue(aviatorObjects[0], env);
        int scale = FunctionUtils.getNumberValue(aviatorObjects[1], env).intValue();
        int roundingMode = BigDecimal.ROUND_HALF_UP;
        // 如果带有舍入规则参数，则按照传参进行舍入计算
        if (length == PAR_LEN3) {
            roundingMode = FunctionUtils.getNumberValue(aviatorObjects[2], env).intValue();
        }
        BigDecimal result = new BigDecimal(number.toString()).setScale(scale, roundingMode);
        return new AviatorDecimal(result);
    }
}