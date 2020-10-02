package com.samples.anotations;

@TestConfig(platform = TestConfig.Platform.PRODUCTION)
//@TestConfig(platform = TestConfig.Platform.DEVELOPMENT)
//@TestConfig(platform = TestConfig.Platform.TEST)
public class TestExample {

    @Test
    void testA() {
        if (true)
            throw new RuntimeException("This test always failed");
    }

    @Test(enabled = false)
    void testB() {
        if (1 > 10)
            throw new RuntimeException("This test always passed. please debug me!");
    }

    @Test(enabled = true)
    void testC() {
        if (10 > 1) {
            System.out.println("testC always passed too");
        }
    }
}
