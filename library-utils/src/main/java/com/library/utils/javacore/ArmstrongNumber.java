package com.library.utils.javacore;

/**
 * Created by feifei.liu on 2017/5/10.
 */
public class ArmstrongNumber {
    public static void main(String[] args) {
        armstrongNumber(153);
    }
    public static void armstrongNumber(int num) {
        int n = num;
        int check = 0,remainder;
        while (num > 0) {
            remainder = num%10;
            check = check + (int)Math.pow(remainder, 3);
            num = num/10;
        }
        if (check == 0) {
            System.out.println(n+" is an Armstrong Number");
        } else {
            System.out.println(n+" is not a Armstrong Number");
        }
    }
}
