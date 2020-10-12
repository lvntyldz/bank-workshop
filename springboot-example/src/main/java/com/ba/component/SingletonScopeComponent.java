package com.ba.component;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Component
public class SingletonScopeComponent {

    private static int instanceCount;

    public SingletonScopeComponent() {
        instanceCount++;
    }

    public void printInstanceInfo() {
        System.out.println("SingletonScope instanceCount : " + instanceCount + " - hashCode : " + this.hashCode());
    }
}
