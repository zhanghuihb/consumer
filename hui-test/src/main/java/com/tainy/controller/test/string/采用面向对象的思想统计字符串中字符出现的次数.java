package com.tainy.controller.test.string;

/**
 * @author Tainy
 * @date 2018/5/25 15:05
 */
public class 采用面向对象的思想统计字符串中字符出现的次数 {

    private static void test1(){
        String str = "abcdrfgytdaascvb";
        int[] a = new int[127];
        for(int i = 0; i < str.length(); i++){
            a[str.charAt(i)]++;
        }
        for(int i = 0; i < a.length; i++){
            if(a[i] != 0){
                System.out.println("字符：" + (char)i + "出现" + a[i] + "次");
            }
        }
    }

    private static void test2(){
        int[] arr = {1,4,1,4,2,5,4,5,8,7,8,77,88,5,4,9,6,2,4,1,5};
    }

    public static void main(String[] args) {

    }
}
