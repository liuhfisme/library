package com.library.utils.operator;

/**
 * 运算符示例.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-03-14
 */
public class OperatorDemo {
    public static void main(String[] args) {
        method_4();
    }

    /**
     * & 按位于
     * <br/>& 按位与的运算规则是将两边的数转换为二进制位，然后运算最终值，运算规则即(两个为真才为真)1&1=1 , 1&0=0 , 0&1=0 , 0&0=0
     */
    public static void method_1() {
        // ← 0000 0011
        // → 0000 0101
        // ↓ 0000 0001
        int x = 3 & 5;
        // ← 0000 0101
        // → 0000 0111
        // ↓ 0000 0101
        int y = 5 & 7;
        System.out.println("x="+x+"\ny="+y);
    }

    /**
     * | 按位或
     * <br/>| 按位或和&按位与计算方式都是转换二进制再计算，不同的是运算规则(一个为真即为真)1|0 = 1 , 1|1 = 1 , 0|0 = 0 , 0|1 = 1
     */
    public static void method_2() {
        // ← 0000 0011
        // → 0000 0101
        // ↓ 0110 0111
        int x = 3 | 5;
        // ← 0000 0101
        // → 0000 0111
        // ↓ 0000 0111
        int y = 5 | 7;
        System.out.println("x="+x+"\ny="+y);
    }

    /**
     * ^ 按位异或
     * <br/>^ 异或运算符顾名思义，异就是不同，其运算规则为1^0 = 1 , 1^1 = 0 , 0^1 = 1 , 0^0 = 0
     */
    public static void method_3() {
        // ← 0000 0011
        // → 0000 0101
        // ↓ 0000 0110
        int x = 3 ^ 5;
        // ← 0000 0101
        // → 0000 0111
        // ↓ 0000 0010
        int y = 5 ^ 7;
        System.out.println("x="+x+"\ny="+y);
    }

    /**
     * << 按位左移
     * <br/> x<<y的意思为x的二进制位往左挪y位，右边补0 ，正数左边第一位补0，负数补1
     */
    public static void method_4() {
        // ← 0000 0011
        // → 5
        // ↓ 0110 0000
        int x = 3 << 5;
        // ← 0000 0101
        // → 7
        // ↓ 0010 1000 0000
        int y = 5 << 7;
        System.out.println("x="+x+"\ny="+y);
    }

    /**
     * << 按位右移
     * <br/>x>>y的意思为x的二进制位往右挪y位，右边补0，正数左边第一位补0，负数补1
     */
    public static void method_5() {
        // ← 0000 0011
        // → 5
        // ↓ 0000 0000
        int x = 3 >> 5;
        // ← 0000 0101
        // → 7
        // ↓ 0000 0000
        int y = 5 >> 7;
        System.out.println("x="+x+"\ny="+y);
    }

}