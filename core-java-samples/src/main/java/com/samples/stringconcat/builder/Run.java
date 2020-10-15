package com.samples.stringconcat.builder;

import com.samples.stringconcat.Constants;

public class Run {

    public static void main(String[] args) {
        System.gc();
        long start = System.currentTimeMillis();
        long startMemory = Runtime.getRuntime().freeMemory();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Constants.STRING_APPEND_LIMIT; i++) {
            sb.append(":").append(i);
        }
        long end = System.currentTimeMillis();
        long endMemory = Runtime.getRuntime().freeMemory();

        System.out.println("--- StringBuilder performance ---");
        System.out.println("Time Taken:" + (end - start));
        System.out.println("Memory used:" + (startMemory - endMemory));
    }
}

/*
süre:3.5 sn
--- StringBuilder performance ---
Time Taken:3685
Memory used:5719912
 */
