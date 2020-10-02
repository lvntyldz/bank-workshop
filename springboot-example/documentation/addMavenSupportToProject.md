projenin maven projesine dönüştürülmesi 2 aşamadan ibarettir.
pom.xml dosyasının oluşturulması ve içine aşağıdaki tanımların yapılmasıyla projeye maven desteği eklenmiş olur.
- groupId:projenin kimliği (projenin varsayılan paket yapısı kullanılır genelde)
- artifactId:projenin alt kimliği
- version : proje versiyonu
- java.version : Java versiyonu

1.  projede root dizinde pom.xml dosyası oluşturulur
    ```
    touch pom.xml
    ```

2. oluşturulan pom.xml dosyasına proje bilgilerinin girilmesi
    ```
    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <groupId>com.ba</groupId>
    <artifactId>springboot-example</artifactId>
    <version>1.0-SNAPSHOT</version>
    
    <properties>
    <java.version>11</java.version>
    </properties>
    </project>
    ```

[index için tıklayın](../README.md)
