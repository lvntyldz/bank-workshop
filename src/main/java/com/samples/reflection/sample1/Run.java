package com.samples.reflection.sample1;

import java.lang.reflect.Method;

/**
 * java.util.Stack class'ı içindeki methodları ekrana yazan bir örnek
 * oracle.com'da "simple reflection example" olarak yayınlanmıştır
 */
public class Run {

    public static void main(String[] args) {

        try {
            Class c = Class.forName("java.util.Stack");
            Method m[] = c.getDeclaredMethods();
            for (int i = 0; i < m.length; i++)
                System.out.println(m[i].toString());
        } catch (Throwable e) {
            System.err.println(e);
        }

    }
}
