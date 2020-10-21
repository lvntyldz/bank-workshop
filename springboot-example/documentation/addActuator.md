* ### Notlar
    Actuator servisleri aracılığıyla Uygulamanın genel durumuyla alakalı bilgilere erişilebilir. Yada bu bilgilerin bazıları(ÖRN:Logger) üzerinde değişiklik yapılabilir.
    - kurumsal uygulamalarda ihtiyaç duyulan bir modüldür.
    - Uygulamanın Run time da ayakta olup olmadığına bakılabilir.
    - JVM in heap size'ı sorgulanabilir
    - JVM üzerindeki sınıflar gözlemlenebilir.
    - İşletim sistemindeki process sayısı incelenebilir.
    - HTTP üzerinden üstteki hizmetleri verebilir. 
    
* ### Properties
    - Varsayılan olarak bütün actuator servisleri aktif gelir. Anck web tarafında kısıtlı servisler aktif olarak gelmektedir.
    - Disable edilmek istenen servis properties dosyasından kapatılabilir.
    - Web tarafında tüm endpointleri aktif etmek için aşğıdaki tanım properties'e eklenebilir.
    ```
    management.endpoints.web.exposure.include=*
    ```    
    
* ### Bağımlılıklar
    ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    ```

* ### Http endpoint ler
    Url | açıklama |
    --- | --- |  
    http://localhost:8080/actuator/beans | Uygulamadaki tüm spring beanlerinin listesini verir | 
    http://localhost:8080/actuator/caches| Kullanılabilir cacheleri listeler  | 
    http://localhost:8080/actuator/configprops | @ConfigurationProperties'in tüm listesini döner | 
    http://localhost:8080/actuator/env| ConfigurableEnvironment taki tüm propertyleri döner | 
    http://localhost:8080/actuator/health| Uygulama durum(health) bilgilerini gösterir. | 
    http://localhost:8080/actuator/loggers|Uygulamadaki log konfigurasyonlarını gösterir  | 
    http://localhost:8080/actuator/liquibase|Uygulanan tüm Liquibase veritabanı geçişlerini gösterir | 
    http://localhost:8080/actuator/threaddump|thread dump dökümünü verir. |
    
    **Thread Dump**  JVM'in dump alındığı anda tüm Thread'lerinin ne durumda olduğunu gösteren bir bilgi kümesidir 

* ### Kaynaklar
    - https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html 

[index için tıklayın](../README.md)
