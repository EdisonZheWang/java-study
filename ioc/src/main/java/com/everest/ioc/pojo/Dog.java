package com.everest.ioc.pojo;

import org.springframework.stereotype.Component;

/**
 * @Date: 2020/9/13
 * @author: <a href="mailto:chrdw.p@gmail.com">Edison Zhe Wang</a>
 */
@Component
public class Dog extends Pet  {
    private String name;
    private int age;

    public Dog() {
        System.out.println("dog 无参");
    }

    public Dog(String name) {
        this.name = name;
        System.out.println("dog name 构造器");
    }

    public Dog(int age) {
        this.age = age;
        System.out.println("dog age 构造器");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
