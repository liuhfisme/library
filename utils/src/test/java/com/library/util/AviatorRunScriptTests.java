package com.library.util;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * Aviator表达式 5.x.
 * @see [https://www.yuque.com/boyan-avfmj/aviatorscript]
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2020-05-28
 */
public class AviatorRunScriptTests {
    private String scriptRootPath = "E:\\IdeaProjects\\library\\utils\\src\\test\\java\\com\\library\\util\\av\\";

    @Test
    public void salaryTest() throws IOException {
        Expression exp = AviatorEvaluator.getInstance().compileScript(scriptRootPath+"salary.av");
        exp.execute();
    }

    /**
     * 编译和执行 Hello World
     *  1. 新版本采用 AviatorScript 脚本方式
     *  2. 这段代码非常简单，调用 println 函数，打印字符串 hello, AviatorScript! 。
     */
    @Test
    public void test1() throws IOException {
        Expression exp = AviatorEvaluator.getInstance().compileScript(scriptRootPath+"hello.av");
        exp.getVariableNames().forEach(item -> {
            System.out.println(item);
        });
//        Expression exp = AviatorEvaluator.compile("print('hello AviatorEvaluator');");
        exp.execute(exp.newEnv("a", "11", "b", 22, "c", "cccc"));
    }

    /**
     * 基本类型及运算
     *  1. 数字
     *      数字包括整数和浮点数，AviatorScript 对 java 的类型做了缩减和扩展，同时保持了一直的运算规则。
     *  2. 整数和算术运算
     *      整数例如 -99、0、1、2、100……等等，对应的类型是 java 中的 long 类型。
     *      AviatorScript 中并没有 byte/short/int 等类型，统一整数类型都为 long，支持的范围也跟 java 语言一样
     */
    @Test
    public void test2() {

    }
}
