### pom.xml tarafındaki değişiklikler
* Spring desteğinin eklenmesi
    ```
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.4.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    ```

* bağımlılıkların tanımlanması
    ```
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
    </dependencies>
    ```

* build tanımının yapılması
    ```
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
    ```


* üstteki değişikliklerden sonra pom.xml dosyasının son hali aşağıdaki gibi olmalıdır
    ```
    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    
        <modelVersion>4.0.0</modelVersion>
        <groupId>com.ba</groupId>
        <artifactId>springboot-example</artifactId>
        <version>1.0-SNAPSHOT</version>
    
        <parent>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-parent</artifactId>
            <version>2.3.4.RELEASE</version>
            <relativePath/> <!-- lookup parent from repository -->
        </parent>
    
        <properties>
            <java.version>11</java.version>
        </properties>
    
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter</artifactId>
            </dependency>
        </dependencies>
    
        <build>
            <plugins>
                <plugin>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-maven-plugin</artifactId>
                </plugin>
            </plugins>
        </build>
    </project>
    ```


<br/>
---
<br/>

### Java tarafındaki değişiklikler
main methodun olduğu classta aşağıdaki değişiklikler yapılır.

* importların eklenmesi
    ```
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    ```
* projenin spring boot uygulaması olduğu annotationlar aracılığıyla belirtilir
    ```
    @SpringBootApplication
    ```

* main method içinde projeni başlayacağı Class SpringApplication.run(...) ile  çağrılır.
    ```
    SpringApplication.run(Run.class, args);
    ```

Run.java dosyasının son hali aşağıdaki gibi olmalıdır
```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Run {

    public static void main(String[] args) {
        SpringApplication.run(Run.class, args);
    }

}
```

[index için tıklayın](../README.md)
