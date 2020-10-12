package com.ba.controller;

import com.ba.component.PrototypeScopeComponent;
import com.ba.component.RequestScopeComponent;
import com.ba.component.SessionScopeComponent;
import com.ba.component.SingletonScopeComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("scopes")
public class ScopeController {

    @Autowired
    RequestScopeComponent requestComponent;

    @Autowired
    private SessionScopeComponent sessionComponent;

    @Autowired
    private SingletonScopeComponent singletonComponent;

    @Autowired
    private PrototypeScopeComponent prototypeComponent;

    @GetMapping("/session")
    public long getSessionScopeData() {
        sessionComponent.printInstanceInfo();
        return System.identityHashCode(sessionComponent);
    }

    @GetMapping("/request")
    public long getRequestScopeData() {
        requestComponent.printInstanceInfo();
        return System.identityHashCode(requestComponent);
    }

    @GetMapping("/singleton")
    public long getSingletonScopeData() {
        singletonComponent.printInstanceInfo();
        return System.identityHashCode(singletonComponent);
    }

    @GetMapping("/prototype")
    public long getPrototypeScopeData() {
        prototypeComponent.printInstanceInfo();
        return System.identityHashCode(prototypeComponent);
    }
}
