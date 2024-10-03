package com.easehire.application.utility;

import java.util.Arrays;

public class Debug {
    public static void debug(Object...obj){
        System.out.println(Arrays.deepToString(obj));
    }
}
