package com.samples.reflection.sample4;

import com.google.gson.Gson;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Run {

    public static void main(String[] args) throws ClassNotFoundException {

        Class cls;// = Class.forName("com.google.gson.Gson");
        Gson gson = convertAndPrintArrayToJson();
        cls = gson.getClass();

        //prints
        printClassMethods(cls);
        printClassPrivateFields(cls);
        printClassFinalFields(cls);
        printClassStaticFields(cls);
        printClassConstructors(cls);

    }

    static Gson convertAndPrintArrayToJson() {
        Gson gson = new Gson();
        String[] lang = {"Java", "Node", "Kotlin", "JavaScript"};
        String json = gson.toJson(lang);

        System.out.println(json);
        printNewLineDivider();
        return gson;
    }

    private static void printClassPrivateFields(Class cls) {
        Arrays.stream(cls.getDeclaredFields()).filter(f -> Modifier.isPrivate(f.getModifiers())).forEach(f -> {
            System.out.println(" private : " + f);
        });
        printNewLineDivider();
    }

    private static void printClassConstructors(Class cls) {
        Arrays.stream(cls.getConstructors()).forEach(c -> {
            System.out.println("constructor : " + c);
        });
        printNewLineDivider();
    }

    private static void printClassStaticFields(Class cls) {
        Arrays.stream(cls.getDeclaredFields()).filter(s -> Modifier.isStatic(s.getModifiers())).forEach(s -> {
            System.out.println(" static : " + s);
        });

        printNewLineDivider();
    }

    private static void printNewLineDivider() {
        System.out.println("---------- ---------- ---------- ----------");
    }

    private static void printClassFinalFields(Class cls) {
        Arrays.stream(cls.getDeclaredFields()).filter(f -> Modifier.isFinal(f.getModifiers())).forEach(f -> {
            System.out.println(" final : " + f);
        });
        printNewLineDivider();
    }

    private static void printClassMethods(Class cls) {

        Method methods[] = cls.getDeclaredMethods();

        Arrays.stream(methods).forEach(method -> {
            System.out.println("method : " + method);
            Arrays.stream(method.getDeclaredAnnotations()).forEach(b -> {
                System.out.println("b--" + b.toString());
            });

        });
        printNewLineDivider();
    }
}
