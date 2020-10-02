package com.jar;

public final class Log {

    private static final String DEBUG_PREFIX = "DEBUG   : ";
    private static final String INFO_PREFIX = "INFO    : ";
    private static final String WARN_PREFIX = "WARNING : ";
    private static final String ERR_PREFIX = "ERROR   : ";

    //block instance generate
    private Log() {
    }

    public static void debug(String msg) {
        System.out.println(DEBUG_PREFIX + msg);
    }

    public static void info(String msg) {
        System.out.println(INFO_PREFIX + msg);
    }

    public static void warning(String msg) {
        System.out.println(WARN_PREFIX + msg);
    }

    public static void error(String msg) {
        System.out.println(ERR_PREFIX + msg);
    }
}
