package com.library.util;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Options;
import org.junit.Test;

/**
 * Aviator表达式.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-04-19
 */
public class AviatorTests {
    /**
     * 简单计算
     *  1. Aviator的数值类型仅支持Long和Double, 任何整数都将转换成Long, 任何浮点数都将转换为Double, 包括用户传入的变量数值。
     *  2. 如果开启了 ALWAYS_PARSE_FLOATING_POINT_NUMBER_INTO_DECIMAL 选项，那么在表达式中出现的浮点数都将解析为 BigDecimal
     */
    @Test
    public void test1() {
        AviatorEvaluator.setOption(Options.ALWAYS_PARSE_FLOATING_POINT_NUMBER_INTO_DECIMAL, true);
        Long result = (Long) AviatorEvaluator.execute("1+2+3");
        System.out.println(result);
    }

    /**
     * 多行表达式
     *  1. 从4.0.0开始，aviator支持以分号；隔开的多行表达式，对于多行表达式求值的结果将是最后一个表达式的结果
     *  2. 通过 Options.TRACE_EVAL来跟踪执行过程
     */
    @Test
    public void test2() {
        AviatorEvaluator.setOption(Options.TRACE_EVAL, true);
        Object result = AviatorEvaluator.execute("print('hello world'); 1+2+3; 100-1");
        System.out.println(result);
    }
}