package com.urise.webapp;

public class LazySingleton {
    volatile private static LazySingleton INSTANCE;

    private LazySingleton() {

    }

    private static class LazySingletonHolder {
        private static final LazySingleton INSTANCE = new LazySingleton();
    }

    public static LazySingleton getInstance() {
        return LazySingletonHolder.INSTANCE;
    }

//    public static com.urise.webapp.LazySingleton getInstance() {
//        if (INSTANCE == null) {
//            synchronized (com.urise.webapp.LazySingleton.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new com.urise.webapp.LazySingleton();
//                }
//            }
//        }
//        return INSTANCE;
//    }
}
