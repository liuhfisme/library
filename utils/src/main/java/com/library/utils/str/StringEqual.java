package com.library.utils.str;

/**
 * @ClassName: StringEqual
 * @Description: 字符串创建对比
 * @author feifei.liu
 * @date 2018/11/29 10:15
 */
public class StringEqual {
    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = new String("ab");
        String s3 = "a";
        String s4 = "b";
        String s5 = "a"+"b";
        String s6 = s3+s4;
        System.out.println(s1 == s2);
        System.out.println(s1 == s5);
        System.out.println(s1 == s6);
        System.out.println(s1 == s6.intern());
        System.out.println(s2 == s2.intern());
        /*
         * String 对象的intern()方法会得到字符串对象在常量池中对应的版本的引用（如果常量池中有一个字符串与String对象的equals结果是true），
         * 如果常量池中没有对应的字符串，则该字符串将被添加到常量池中，然后返回常量池中字符串的引用；
         *
         * 字符串的 + 操作其本质是创建了StringBuilder对象进行append操作，然后将拼接后的StringBuilder对象用toString()方法处理成String对象，
         * 这一点可以用javap -c StringEqualTest.class 命令获得class文件对应的JVM字节码指令就可以看出来
         */

        String sa = "ab";
        String sb = "ab";
        System.out.println(sa.equals(sb));

        Integer.valueOf("0");
        Long l1 = null;
        long l2 = l1+1;
    }
}
