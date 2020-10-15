package com.samples.stringconcat.buffer;

import com.samples.stringconcat.Constants;

public class Run {

    public static void main(String[] args) {
        System.gc();

        long start = System.currentTimeMillis();
        long startMemory = Runtime.getRuntime().freeMemory();

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < Constants.STRING_APPEND_LIMIT; i++) {
            sb.append(":").append(i);
        }

        long end = System.currentTimeMillis();
        long endMemory = Runtime.getRuntime().freeMemory();

        System.out.println("--- StringBuffer performance ---");
        System.out.println("Time Taken:" + (end - start));
        System.out.println("Memory used:" + (startMemory - endMemory));
    }
}
/*
sonuç: 4 sn
--- StringBuffer performance ---
Time Taken:4885
Memory used:3044424
 */
