package com.ba.component;

import org.springframework.stereotype.Component;

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
