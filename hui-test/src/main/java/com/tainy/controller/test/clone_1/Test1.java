package com.tainy.controller.test.clone_1;

/**
 * @author Tainy
 * @date 2018/5/23 15:30
 */
public class Test1 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person person = new Person(25, "张三");
        Person person1 = (Person) person.clone();
        System.out.println(person);
        System.out.println(person1);
    }
}
