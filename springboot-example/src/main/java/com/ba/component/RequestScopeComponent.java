package com.ba.component;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class RequestScopeComponent {

    private static int instanceCount;

    public RequestScopeComponent() {
        instanceCount++;
    }

    public void printInstanceInfo() {
        System.out.println("RequestScope instanceCount : " + instanceCount + " - hashCode : " + this.hashCode());
    }
}
