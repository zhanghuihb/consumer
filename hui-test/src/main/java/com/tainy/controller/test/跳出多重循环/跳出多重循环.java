package com.tainy.controller.test.跳出多重循环;

/**
 * @author Tainy
 * @date 2018/5/24 9:47
 */
public class 跳出多重循环 {
    public static void main(String[] args) {
        int a = 0;
        int b = 0;
        here:
        for (int i = 0; i < 10 ;i++){
            a++;
            for (int j = 0; j < 10; j++){
                b++;
                if(j == 5){
                    break here;
                }
            }
        }
        System.out.println(a);
        System.out.println(b);
    }
}
