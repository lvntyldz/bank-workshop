package com.samples.stringconcat.string;

import com.samples.stringconcat.Constants;

public class Run {

    public static void main(String[] args) {
        System.gc();

        long start = System.currentTimeMillis();
        long startMemory = Runtime.getRuntime().freeMemory();

        String str = new String();
        for (int i = 0; i < Constants.STRING_APPEND_LIMIT / 1000; i++) {
            str += ":" + i;
        }

        long end = System.currentTimeMillis();
        long endMemory = Runtime.getRuntime().freeMemory();

        System.out.println("--- String performance ---");
        System.out.println("Time Taken:" + (end - start));
        System.out.println("Memory used:" + (startMemory - endMemory));
    }

    /*
    sonuç:15 sn
    --- String performance ---
    Time Taken:15695
    Memory used:-600409000
    */
}
