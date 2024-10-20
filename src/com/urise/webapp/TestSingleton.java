package com.urise.webapp;

public class TestSingleton {
    private static final TestSingleton instance = new TestSingleton();

    public static TestSingleton getInstance() {
        return instance;
    }

    private TestSingleton() {
    }
}
