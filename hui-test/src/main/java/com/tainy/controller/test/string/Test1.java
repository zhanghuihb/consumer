package com.tainy.controller.test.string;

/**
 * @author Tainy
 * @date 2018/5/24 16:25
 */
public class Test1 {
    public static void main(String[] args) {

        String s1 = "HelloWorld!";
        String s2 = "Hello";
        String s3 = "World!";
        String s4 = s2 + s3;
        String s5 = "Hello" + "World!";
        System.out.println(s1 == s4);
        System.out.println(s1 == s4.intern());
        System.out.println(s1 == s5);
    }
}
