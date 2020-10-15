package com.samples.string.sample2;

public class Run {

    public static void main(String[] args) {

        checkStringWithDoubleEqual();
        checkNewReferanceStringWithDoubleEqual();

        checkStringWithEqual();
        checkNewReferanceStringWithEqual();
    }

    private static void checkNewReferanceStringWithDoubleEqual() {
        String s3 = new String("Hello World");
        String s4 = new String("Hello World");

        if (s3 == s4) {//referansları farklı
            System.out.println("s3 == s4");
        } else {
            System.out.println("s3 != s4");
        }
    }

    private static void checkNewReferanceStringWithEqual() {
        String s3 = new String("Hello World");
        String s4 = new String("Hello World");

        if (s3.equals(s4)) {//referansları farklı
            System.out.println("s3.equals(s4)");
        } else {
            System.out.println("s3.equals(s4)");
        }
    }

    private static void checkStringWithDoubleEqual() {
        String s1 = "Hello World";
        String s2 = "Hello World";

        if (s1 == s2) {//Referansları aynı
            System.out.println("s1 == s2");
        } else {
            System.out.println("s1 != s2");
        }
    }

    private static void checkStringWithEqual() {
        String s1 = "Hello World";
        String s2 = "Hello World";

        if (s1.equals(s2)) {
            System.out.println("s1.equals(s2)");
        } else {
            System.out.println("!s1.equals(s2)");
        }
    }

}
