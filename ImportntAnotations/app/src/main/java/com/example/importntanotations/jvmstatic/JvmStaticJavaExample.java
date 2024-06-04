package com.example.importntanotations.jvmstatic;

public class JvmStaticJavaExample {

    public static void main(String[] args) {
        Constants.INSTANCE.whoIsMohabNotAnnotated();
        Constants.whoIsMohabAnnotated();
    }
}
