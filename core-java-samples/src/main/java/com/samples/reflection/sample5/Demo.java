package com.samples.reflection.sample5;

import com.jar.Log;

import java.util.Arrays;

public class Demo {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {

        Log.info("inspecting started....");

        Class cls = Class.forName("com.jar.Log");

        //tanımlı constructorları görüntüle
        Arrays.stream(cls.getDeclaredConstructors()).forEach(c -> {
            System.out.println("constructor data : " + c);
        });

        //tanımlı methodları görüntüler
        Arrays.stream(cls.getMethods()).forEach(m -> {
            System.out.println("method data : " + m);
        });

        //tanımlı fieldları görüntüler
        Arrays.stream(cls.getDeclaredFields()).forEach(f -> {
            System.out.println("field data : " + f);
        });

        Log.info("inspect completed....");
    }
}
