package com.ba.component;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class SessionScopeComponent {

    private static int instanceCount;

    public SessionScopeComponent() {
        instanceCount++;
    }

    public void printInstanceInfo() {
        System.out.println("SessionScope instanceCount : " + instanceCount + " - hashCode : " + this.hashCode());
    }
}
