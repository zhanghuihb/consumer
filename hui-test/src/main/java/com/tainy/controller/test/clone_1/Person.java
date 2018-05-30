package com.tainy.controller.test.clone_1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tainy
 * @date 2018/5/23 15:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Cloneable{

    private Integer age;

    private String name;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
