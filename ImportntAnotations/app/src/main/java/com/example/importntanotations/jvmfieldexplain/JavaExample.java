package com.example.importntanotations.jvmfieldexplain;

import java.util.Date;

public class JavaExample {


    public static void main(String[] args) {
        Person person = new Person("mohab", new Date());

        String name = person.name;
    }
}
