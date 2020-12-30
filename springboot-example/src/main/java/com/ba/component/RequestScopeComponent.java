package com.ba.component;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.io.Serializable;

@Component
@RequestScope
public class RequestScopeComponent implements Serializable {

  private static int instanceCount;

  public RequestScopeComponent() {
    instanceCount++;
  }

  public void printInstanceInfo() {
    System.out.println(
        "RequestScope instanceCount : " + instanceCount + " - hashCode : " + this.hashCode());
  }
}
