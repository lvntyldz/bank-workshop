package com.samples.anotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)//runtime da devrede olacak
@Target(ElementType.TYPE) //class larda kullanılacak
@Documented
public @interface TestConfig {

    Platform platform() default Platform.DEVELOPMENT;

    enum Platform {PRODUCTION, DEVELOPMENT, TEST}

}
