package com.samples.string.sample2;

public class Run {

    public static void main(String[] args) {

        String s1 = "Hello World";
        String s2 = "Hello World";

        if (s1 == s2) {//referance ları aynı
            System.out.println("s1 == s2 ");
        } else {
            System.out.println("s1 != s2 ");
        }

        String s3 = new String("Hello World");
        String s4 = new String("Hello World");

        if (s3 == s4) {//referansları farklı
            System.out.println("s3 == s4 ");
        } else {
            System.out.println("s3 != s4 ");
        }


    }
}
