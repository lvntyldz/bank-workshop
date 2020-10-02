##### ilk olarak spring-boot-starter yerine spring-boot-starter-web bağımlılığına geçilir.

spring-boot-starter-web aşağıdaki dependency leri de içerir

- spring-boot-starter
- jackson
- spring-core
- spring-mvc
- spring-boot-starter-tomcat

pom.xml'e aşğıdaki şekilde güncellenmelidir.
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

<br/>
--- 
<br/>

HelloController adında bir Class oluşturulur.
```
$ touch HelloController.java
```

HelloController class'ı @RestController olarak işaretlenir.
```
@RestController
public class HelloController {...}
```

###### NOT: @RestController = @Controller + @ResponseBody

return type'ı String olan sayHello(...) adında bir method tanımlanır.
```
    @GetMapping("hi")
    public String sayHello() {
        return "Hello from controller";
    }
```

HelloController.java dosyasının son hali aşağıdaki gibi olmalıdır.
```
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("hi")
    public String sayHello() {
        return "Hello from controller";
    }
}
```

uygulama ayağa kaldırılıp aşağıdaki adres ziyaret edilir

URL: http://localhost:8080/hi


[index için tıklayın](../README.md)
