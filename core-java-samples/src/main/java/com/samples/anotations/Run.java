package com.samples.anotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Run {

    public static void main(String[] args) throws Exception {

        int passed = 0, failed = 0, count = 0, ignore = 0;

        Class<TestExample> obj = TestExample.class;
        TestConfig testConfig = null;

        // Process @TestConfig
        if (obj.isAnnotationPresent(TestConfig.class)) {
            Annotation testInfoAnnotation = obj.getAnnotation(TestConfig.class);
            testConfig = (TestConfig) testInfoAnnotation;
            System.out.printf("%nPlatform :%s \n", testConfig.platform());
        }

        // Process @Test
        for (Method method : obj.getDeclaredMethods()) {

            // if method is annotated with @Test
            if (method.isAnnotationPresent(Test.class)) {

                Annotation annotation = method.getAnnotation(Test.class);
                Test test = (Test) annotation;

                // if enabled = true (default)
                if (test.enabled() || TestConfig.Platform.DEVELOPMENT.name().equals(testConfig.platform().name())) {

                    try {
                        method.invoke(obj.newInstance());
                        System.out.printf("%s - Test '%s' - passed %n", ++count, method.getName());
                        passed++;
                    } catch (Throwable ex) {
                        System.out.printf("%s - Test '%s' - failed: %s %n", ++count, method.getName(), ex.getCause());
                        failed++;
                    }

                } else {
                    System.out.printf("%s - Test '%s' - ignored%n", ++count, method.getName());
                    ignore++;
                }

            }
        }

        System.out.printf("%nResult : Total : %d, Passed: %d, Failed %d, Ignore %d%n", count, passed, failed, ignore);
    }

}
