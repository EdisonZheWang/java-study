package com.everest.ioc.pojo;

import org.springframework.stereotype.Component;

/**
 * @Date: 2020/9/12
 * @author: <a href="mailto:chrdw.p@gmail.com">Edison Zhe Wang</a>
 */
@Component
public class Person {

    private String name;
    private int age;
    private Pet pet;

    public Person() {
        System.out.println("person 无参");
    }

    public Person(String name) {
        this.name = name;
        System.out.println("person name构造器");

    }

    public Person(int age) {
        this.age = age;
        System.out.println("person age构造器");

    }

    public Person(String name, int age, Pet pet) {
        this.name = name;
        this.age = age;
        this.pet = pet;
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

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
