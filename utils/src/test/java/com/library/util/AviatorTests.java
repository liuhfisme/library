package com.library.util;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.Options;
import com.googlecode.aviator.lexer.token.OperatorType;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.AbstractVariadicFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Aviator表达式.
 * @see [https://github.com/killme2008/aviator/wiki]
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
        long result = (Long) AviatorEvaluator.execute("1+2+3");
        Assert.assertEquals(6L, result);
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

    /**
     * 使用变量
     */
    @Test
    public void test3() {
        String yourName = "feifei.liu";
        Map<String, Object> env = new HashMap<>(16);
        env.put("yourName", yourName);
        String result = (String) AviatorEvaluator.execute("'hello ' + yourName", env);
        System.out.println(result);
        yourName = "feifei.work";
        String result2 = (String) AviatorEvaluator.exec("'hello ' + yourName", yourName);
        System.out.println(result2);
    }

    /**
     * 调用函数
     *  1. 从 4.0.0 开始， aviator 支持通过 lambda 关键字定义一个匿名函数，并且支持闭包捕获
     */
    @Test
    public void test4() {
        long length = (long) AviatorEvaluator.execute("string.length('hello')");
        Assert.assertEquals(5, length);
        boolean result = (boolean) AviatorEvaluator.execute("string.contains(\"test\", string.substring('hello', 1, 2))");
        Assert.assertTrue(result);
        long result2 = (long) AviatorEvaluator.exec("(lambda(x,y) -> x+y end)(x,y)", 1,2);
        Assert.assertEquals(3, result2);
        long result3 = (long) AviatorEvaluator.exec("(lambda(x) -> lambda(y) -> lambda(z) -> x+y+z end end end)(1)(2)(3)", 1,2,3);
        Assert.assertEquals(6, result3);
    }

    /**
     * 自定义函数
     *  1. 通过AviatorEvaluator.addFunction注册函数
     *     通过AviatorEvaluator.removeFunction移除函数
     *  2. 如果你的参数个数不确定，可以继承 AbstractVariadicFunction 类，只要实现其中的 variadicCall 方法即可
     *  3. 自定义函数在 4.0.0　之后也可以通过 lambda　来定义
     *  4. 除了通过代码的方式 AviatorEvaluator.addFunction 来添加自定义函数之外，你可以在 classpath 下放置一个配置文件 aviator_functions.config，内容是一行一行的自定义函数类的完整名称
     *     # 这是一行注释
     *     com.example.TestFunction
     *     com.example.GetFirstNonNullFunction
     *
     *     如果你想自定义文件路径，可以通过传入环境变量 -Dcom.googlecode.aviator.custom_function_config_file=xxxx.config
     *  5. 从 4.0.0 开始，Aviator 还支持 FunctionLoader接口，可以用于自定义函数加载器
     */
    @Test
    public void test5() {
        // 1. 注册函数
        AviatorEvaluator.addFunction(new AddFunction());
        Double result = (Double) AviatorEvaluator.execute("add(1, 2)");
        Assert.assertEquals(Double.valueOf(3), result);

        // 2. 注册函数
        AviatorEvaluator.addFunction(new GetFirstNonNullFunction());
        Object result1 = AviatorEvaluator.execute("getFirstNonNull(1)");
        Object result2 = AviatorEvaluator.execute("getFirstNonNull(1,2,3,4,nil,5)");
        Object result3 = AviatorEvaluator.execute("getFirstNonNull(a,b,c,d)");
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);

        // 3. lambda定义
        AviatorEvaluator.defineFunction("add2", "lambda(x,y) -> x+y end");
        long result4 = (long) AviatorEvaluator.execute("add2(1,2)");
        Assert.assertEquals(3, result4);

        // 4. 加载自定义函数列表

        // 5. 函数加载器
    }

    /**
     * 重载运算符
     *  1. & 不是位运算，而是字符串连接符，那么你可以通过 3.3.0 版本支持的运算符重载来实现
     *     AviatorEvaluator.addOpFunction(opType, func) 就可以重载指定的运算符
     */
    @Test
    public void test6() {
        AviatorEvaluator.addOpFunction(OperatorType.BIT_AND, new AbstractFunction() {
            @Override
            public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
                return new AviatorString(arg1.getValue(env).toString() + arg2.getValue(env).toString());
            }

            @Override
            public String getName() {
                return "&";
            }
        });
        Assert.assertEquals("43", AviatorEvaluator.exec("a&3", 4));
    }

    /**
     * 编译表达式
     *  1. 上面提到的例子都是直接执行表达式, 事实上 Aviator 背后都帮你做了编译并执行的工作。 你可以自己先编译表达式, 返回一个编译的结果,
     *     然后传入不同的env来复用编译结果, 提高性能, 这是更推荐的使用方式
     */
    @Test
    public void test7() {
        String expression = "a-(b-c)>100";
        // 编译表达式
        Expression compiledExp = AviatorEvaluator.compile(expression);
        Map<String, Object> env = new HashMap<>(16);
        env.put("a", 100.3);
        env.put("b", 45);
        env.put("c", -199.100);
        // 执行表达式
        Boolean result = (Boolean) compiledExp.execute(env);
        Assert.assertFalse(result);

        // 编译后的结果你可以自己缓存, 也可以交给 Aviator 帮你缓存, AviatorEvaluator内部有一个全局的缓存池, 如果你决定缓存编译结果
        // 将cached设置为true即可, 那么下次编译同一个表达式的时候将直接返回上一次编译的结果
        compiledExp = AviatorEvaluator.compile(expression, true);
        compiledExp.execute(env);

        // 使缓存失效
        AviatorEvaluator.invalidateCache(expression);
    }

    /**
     * 访问数组和集合
     *  1. 可以通过中括号去访问数组和java.util.List对象, 可以通过map.key访问java.util.Map中key对应的value
     *  2. 如果函数调用或者括号表达式结果是一个数组或者List，你同样可以可以通过 [index] 访问
     */
    @Test
    public void test8() {
        final List<String> list = new ArrayList<>();
        list.add("hello");
        list.add(" world");
        final int[] array = new int[]{0, 1, 3};
        final Map<String, Date> map = new HashMap<>(16);
        map.put("date", new Date(System.currentTimeMillis()));
        Map<String, Object> env = new HashMap<>(16);
        env.put("list", list);
        env.put("array", array);
        env.put("map", map);
        Assert.assertEquals("hello world", AviatorEvaluator.execute("list[0]+list[1]", env));
        Assert.assertEquals(4L, AviatorEvaluator.execute("array[0]+array[1]+array[2]", env));
        System.out.println(AviatorEvaluator.execute("'today is '+map.date", env));

        Assert.assertEquals("a", AviatorEvaluator.exec("string.split(s,',')[0]", "a,b,c,d"));
    }

    /**
     * 三元操作符
     *  1. Aviator 不提供if else语句, 但是提供了三元操作符?:用于条件判断,使用上与 java 没有什么不同
     *     Aviator 的三元表达式对于两个分支的结果类型并不要求一致,可以是任何类型,这一点与 java 不同
     */
    @Test
    public void test9() {
        Assert.assertEquals("yes", AviatorEvaluator.exec("a>0? 'yes':'no'", 1));
    }

    /**
     * 正则表达式匹配
     *  1. Aviator 支持类 Ruby 和 Perl 风格的表达式匹配运算,通过=~操作符
     *     Aviator 的正则表达式规则跟 Java 完全一样,因为内部其实就是使用java.util.regex.Pattern做编译的
     *  2. 请注意，分组捕获放入 env 是默认开启的，因此如果传入的 env 不是线程安全并且被并发使用，可能存在线程安全的隐患。
     *     关闭分组匹配，可以通过 AviatorEvaluator.setOption(Options.PUT_CAPTURING_GROUPS_INTO_ENV, false); 来关闭，对性能有稍许好处。
     */
    @Test
    public void test10() {
        // 匹配 email 并提取用户名返回
        String email = "liuff2513@foxmail.com";
        Map<String, Object> env = new HashMap<>(16);
        env.put("email", email);
        Assert.assertEquals("liuff2513", AviatorEvaluator.execute("email=~/([\\w0-8]+)@\\w+[\\.\\w+]+/ ? $1 : 'unknow' ", env));
    }

    /**
     * 变量的语法糖
     *  1. Aviator 有个方便用户使用变量的语法糖, 当你要访问变量a中的某个属性b, 那么你可以通过a.b访问到,
     *     更进一步, a.b.c将访问变量a的b属性中的c属性值, 推广开来也就是说 Aviator 可以将变量声明为嵌套访问的形式
     */
    @Test
    public void test11() {
        TestAviator foo = new TestAviator(100, 3.14f, new Date(System.currentTimeMillis()));
        Map<String, Object> env = new HashMap<>(16);
        env.put("foo", foo);
        Assert.assertEquals(100, AviatorEvaluator.execute("foo.i", env));
        Assert.assertEquals(3.14f, AviatorEvaluator.execute("foo.f", env));
        Assert.assertEquals(4L, AviatorEvaluator.execute("foo.date.month+1", env));
    }

    /**
     * nil 对象
     *  1. nil是 Aviator 内置的常量,类似 java 中的null,表示空的值
     *     nil跟null不同的在于,在 java 中null只能使用在==、!=的比较运算符,而nil还可以使用>、>=、<、<=等比较运算符
     *     Aviator 规定,任何对象都比nil大除了nil本身。用户传入的变量如果为null,将自动以nil替代
     */
    @Test
    public void test12() {
        Assert.assertTrue((Boolean) AviatorEvaluator.execute("nil == nil"));
        Assert.assertTrue((Boolean) AviatorEvaluator.execute("3 > nil"));
        Assert.assertTrue((Boolean) AviatorEvaluator.execute("true != nil"));
        Assert.assertTrue((Boolean) AviatorEvaluator.execute("' ' > nil"));
        Assert.assertTrue((Boolean) AviatorEvaluator.execute("a == nil"));
    }

    /**
     * 日期比较
     *  1. Aviator 并不支持日期类型,如果要比较日期,你需要将日期写字符串的形式,并且要求是形如 “yyyy-MM-dd HH:mm:ss:SS”的字符串,否则都将报错
     *     字符串跟java.util.Date比较的时候将自动转换为Date对象进行比较
     *  2. 也就是说String除了能跟String比较之外,还能跟nil和java.util.Date对象比较
     */
    @Test
    public void teset13() {
        Map<String, Object> env = new HashMap<>(16);
        final Date date = new Date();
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS").format(date);
        env.put("date", date);
        env.put("dateStr", dateStr);
        Boolean result = (Boolean) AviatorEvaluator.execute("date==dateStr", env);
        Assert.assertTrue(result);  // true
        result = (Boolean) AviatorEvaluator.execute("date > '2010-12-20 00:00:00:00' ", env);
        Assert.assertTrue(result);  // true
        result = (Boolean) AviatorEvaluator.execute("date < '2200-12-20 00:00:00:00' ", env);
        Assert.assertTrue(result);  // true
        result = (Boolean) AviatorEvaluator.execute("date==date ", env);
        Assert.assertTrue(result);  // true
    }

    /**
     * 大数计算和精度
     *  1. 从 2.3.0 版本开始,aviator 开始支持大数字计算和特定精度的计算
     *     本质上就是支持java.math.BigInteger和java.math.BigDecimal两种类型, 这两种类型在 aviator 中简称 为big int和decimal类型
     *
     * 字面量表示（big int和decimal的表示与其他数字不同,两条规则:）
     *  1. 以大写字母N为后缀的整数都被认为是big int,如1N,2N,9999999999999999999999N等, 都是big int类型
     *  2. 超过long范围的整数字面量都将自动转换为big int类型
     *  3. 以大写字母M为后缀的数字都被认为是decimal, 如1M,2.222M, 100000.9999M等, 都是decimal类型
     *  4. 如果用户觉的给浮点数添加 M 后缀比较繁琐，也可以强制所有浮点数解析为 BigDecimal，通过代码开启下列选项即可：
     *     AviatorEvaluator.setOption(Options.ALWAYS_PARSE_FLOATING_POINT_NUMBER_INTO_DECIMAL, true);
     *
     * 运算
     *  1. big int和decimal的运算,跟其他数字类型long,double没有什么区别,操作符仍然是一样的
     *     aviator重载了基本算术操作符来支持这两种新类型
     *
     * 类型转换和提升
     *  1. 当big int或者decimal和其他类型的数字做运算的时候,按照long < big int < decimal < double的规则做提升,
     *     也就是说运算的数字如果类型不一致, 结果的类型为两者之间更“高”的类型。例如:
     *     >> 1 + 3N, 结果为big int的4N
     *     >> 1 + 3.1M,结果为decimal的4.1M
     *     >> 1N + 3.1M,结果为decimal的 4.1M
     *     >> 1.0 + 3N,结果为double的4.0
     *     >> 1.0 + 3.1M,结果为double的4.1
     *
     * decimal 的计算精度
     *  1. Java 的java.math.BigDecimal通过java.math.MathContext支持特定精度的计算,任何涉及到金额的计算都应该使用decimal类型
     *     默认 Aviator 的计算精度为MathContext.DECIMAL128,你可以自定义精度, 通过: AviatorEvaluator.setOption(Options.MATH_CONTEXT, MathContext.DECIMAL64);
     */
    @Test
    public void test14() {
        // 大数计算和精度
        //  -> 类似99999999999999999999999999999999这样的数字在 Java 语言里是没办法编译通过 的, 因为它超过了Long类型的范围, 只能用BigInteger来封装。
        //  -> 但是 aviator 通过包装,可 以直接支持这种大整数的计算
        Assert.assertNotNull(AviatorEvaluator.execute("99999999999999999999999999999999 + 99999999999999999999999999999999"));
        //  -> 结果为类型big int的: 199999999999999999999999999999998

        // 字面量表示
        AviatorEvaluator.setOption(Options.ALWAYS_PARSE_FLOATING_POINT_NUMBER_INTO_DECIMAL, true);

        // 运算
        Object rt = AviatorEvaluator.execute("9223372036854775807100.356M * 2");
        System.out.println(rt + " " + rt.getClass());  // 18446744073709551614200.712 class java.math.BigDecimal
        rt = AviatorEvaluator.execute("92233720368547758074+1000");
        System.out.println(rt + " " + rt.getClass());  // 92233720368547759074 class java.math.BigInteger
        BigInteger a = new BigInteger(String.valueOf(Long.MAX_VALUE) + String.valueOf(Long.MAX_VALUE));
        BigDecimal b = new BigDecimal("3.2");
        BigDecimal c = new BigDecimal("9999.99999");
        rt = AviatorEvaluator.exec("a+10000000000000000000", a);
        System.out.println(rt + " " + rt.getClass());  // 92233720368547758089223372036854775807 class java.math.BigInteger
        rt = AviatorEvaluator.exec("b+c*2", b, c);
        System.out.println(rt + " " + rt.getClass());  // 20003.19998 class java.math.BigDecimal
        rt = AviatorEvaluator.exec("a*b/c", a, b, c);
        System.out.println(rt + " " + rt.getClass());  // 2.951479054745007313280155218459508E+34 class java.math.BigDecimal

        // 类型转换和提升

        // decimal 的计算精度
        AviatorEvaluator.setOption(Options.MATH_CONTEXT, MathContext.DECIMAL64);
    }

    /**
     * 强大的 seq 库
     *  1. aviator 拥有强大的操作集合和数组的 seq 库。整个库风格类似函数式编程中的高阶函数。
     *     在 aviator 中, 数组以及java.util.Collection下的子类都称为seq,可以直接利用 seq 库进行遍历、过滤和聚合等操作。
     *     >> 求长度: count(list)
     *     >> 求和: reduce(list,+,0), reduce函数接收三个参数,第一个是seq,第二个是聚合的函数,如+等,第三个是聚合的初始值
     *     >> 过滤: filter(list,seq.gt(9)), 过滤出list中所有大于9的元素并返回集合; seq.gt函数用于生成一个谓词,表示大于某个值
     *     >> 判断元素在不在集合里: include(list,10)
     *     >> 排序: sort(list)
     *     >> 遍历整个集合: map(list,println), map接受的第二个函数将作用于集合中的每个元素,这里简单地调用println打印每个元素
     *
     *     >> seq.some(list, pred) 当集合中只要有一个元素满足谓词函数 pred 返回 true，立即返回 true，否则为 false。
     *     >> seq.every(list, pred) 当集合里的每个元素都满足谓词函数 pred 返回 true，则结果为 true，否则返回 false。
     *     >> seq.not_any(list, pred)，当集合里的每个元素都满足谓词函数 pred 返回 false，则结果为 true，否则返回 false。
     *
     *     >> 以及 seq.or(p1, p2, ...) 和 seq.and(p1, p2, ...) 用于组合 seq.gt、seq.lt 等谓词函数。
     *
     * 两种运行模式
     *  1. 默认 AviatorEvaluator 以执行速度优先:
     *     AviatorEvaluator.setOption(Options.OPTIMIZE_LEVEL, AviatorEvaluator.EVAL);
     *  2. 你可以修改为编译速度优先,这样不会做编译优化:
     *     AviatorEvaluator.setOption(Options.OPTIMIZE_LEVEL, AviatorEvaluator.COMPILE);
     *
     * 调试信息
     *  1. 如果想查看每个表达式生成的字节码，可以通过打开 Trace 选项
     *     AviatorEvaluator.setOption(Options.TRACE, true);
     *  2. 默认是打印到标准输出,你可以改变输出指向:
     *     AviatorEvaluator.setTraceOutputStream(new FileOutputStream(new File("aviator.log")));
     */
    @Test
    public void test15() {
        Map<String, Object> env = new HashMap<>(16);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(20);
        list.add(10);
        env.put("list", list);
        Object result = AviatorEvaluator.execute("count(list)", env);
        System.out.println(result);  // 3
        result = AviatorEvaluator.execute("reduce(list,+,0)", env);
        System.out.println(result);  // 33
        result = AviatorEvaluator.execute("filter(list,seq.gt(9))", env);
        System.out.println(result);  // [10, 20]
        result = AviatorEvaluator.execute("include(list,10)", env);
        System.out.println(result);  // true
        result = AviatorEvaluator.execute("sort(list)", env);
        System.out.println(result);  // [3, 10, 20]
        AviatorEvaluator.execute("map(list,println)", env);
    }
}

/**
 * 自定义ADD函数
 */
class AddFunction extends AbstractFunction {

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        Number left = FunctionUtils.getNumberValue(arg1, env);
        Number right = FunctionUtils.getNumberValue(arg2, env);
        return new AviatorDouble(left.doubleValue() + right.doubleValue());
    }

    @Override
    public String getName() {
        return "add";
    }
}

/**
 * 自定义多参数函数
 */
class GetFirstNonNullFunction extends AbstractVariadicFunction {
    @Override
    public AviatorObject variadicCall(Map<String, Object> env, AviatorObject... args) {
        if (args != null) {
            for (AviatorObject arg:args) {
                if (arg.getValue(env) !=null) {
                    return arg;
                }
            }
        }
        return new AviatorString(null);
    }

    @Override
    public String getName() {
        return "getFirstNonNull";
    }
}


//class FunctionLoaderImpl implements FunctionLoader {
//
//    @Override
//    public AviatorFunction onFunctionNotFound(String name) {
//        if ("add".equals(name)) {
//            System.out.println("add2函数没有找到，将手动注册该函数！");
//            return new AddFunction();
//        }
//        return null;
//    }
//}
