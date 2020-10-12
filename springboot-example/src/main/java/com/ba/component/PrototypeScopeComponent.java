package com.ba.component;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrototypeScopeComponent {

    private static int instanceCount;

    public PrototypeScopeComponent() {
        instanceCount++;
    }

    public void printInstanceInfo() {
        System.out.println("PrototypeScope instanceCount : " + instanceCount + " - hashCode : " + this.hashCode());
    }
}
