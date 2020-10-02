package com.jar;

public class Run {

    public static void main(String[] args) {

        System.out.println("\n***** ***** *****");

        Log.debug("starting app ...");
        Log.info("started...");
        Log.warning("a warning message...");
        Log.error("a error message...");

        System.out.println("***** ***** *****\n");
    }
}
