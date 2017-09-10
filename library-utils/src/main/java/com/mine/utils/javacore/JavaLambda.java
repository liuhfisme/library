package com.mine.utils.javacore;

import java.util.Arrays;

/**
 * Created by feifei.liu on 2017/4/15.
 * Lambda表达式（也叫做闭包）
 */
public class JavaLambda {
    public static void main(String[] args) {
        Arrays.asList("a","b","d").forEach(e-> System.out.printf(e));
    }
}
