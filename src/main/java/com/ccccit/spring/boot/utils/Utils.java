package com.ccccit.spring.boot.utils;

public class Utils {
    private static long index = 0;
    public static long getNextId(){
        return ++index;
    }
}
