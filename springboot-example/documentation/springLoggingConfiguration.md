* ### Referanslar
    - Bu çalışmadaki configurasyon **application.properties** adlı dosyada yapılmıştır.
    **- Çalışma yapıldıktan sonra log configurasyonları tekrar eski haline getirilmesi için yorum satırına dönüştürülmüştür. Çalışma incelenirken kapatılan konfigurasyonlar yeniden aktif edilmelidir.**

* ### Açıklamalar
    - Spring boot log alt yapısı olarak  **Java Util Logging, Log4J2, ve Logback** kütüphanelerini  desteklenir.
    - Varsayılanda **logback** aktif olarak gelir.    
    - Log ayarları **application.properties** dosyasından değişebileceği gibi kütüphanenin kendi log dosyasından da değişebilir**(logback.xml)**
    - Log seviyeleri aşağıdaki şekilde tanımlıdır.
    
        | logging levels |
        | --- |
        | FATAL |
        | ERROR |
        | WARN |
        | INFO |
        | DEBUG |
        | TRACE |

        burada aşağı doğru indikçe bütün loglar açılır. Yukarı doğru çıktıkça loglar seviyelerine göre kapanır. 
    - Varsayılan durumda log mesajları console'a yazılır. Ancak **application.properties** dosyası içinde  **logging.file** tanımlanırsa loglar buraya yazılır. Özel bir dizine log yazdırmak için ise **logging.path** parametresi kullanılır.

        logging.file | logging.path |Örnek | Açıklama |
        | --- | --- |  --- | --- |
        | tanımsız | tanımsız | | tanımsız ise console a log yazılır.
        | özel bir dosya | tanımsız | myLogFile.log | Bulunulan directory de myLogFile.log adlı dosyaya yazar.
        | tanımsız | /var/log/ | /var/log/ | spring.log dosyasını belirilen(/var/log) dizine yazar.
    
* ### Logların dosyaya yazılması
    Logların **/target/myLoggingFiles** dizini altında yazılması için aşağıdaki parametre **application.properties** dosyasına eklenir.
   
    ```
    logging.file.path=./target/myLoggingFiles
    ```
  
    Bu işlemden sonra uygulama logları **target/myLoggingFiles/spring.log** dosyasına yazılacaktır.
    
* ### Log seviyesinin değiştirilmesi
    Uygulamada uyarı(warn) bilgisinin altındaki loglar yazılmasın diye aşağıdaki parametreler application.properties dosyasına yazılır.
    ```
    logging.level.root=warn
    logging.level.org.springframework.web=warn
    logging.level.org.hibernate=warn
    logging.level.org.hibernate.stat=warn
    logging.level.org.hibernate.type=warn
    logging.level.liquibase=warn
    ```

    Bu değişikliklerden sorna **target/myLoggingFiles/spring.log** dosyası incelendiğinde **trace** ve **debug** loglarının artık yazılmadığı gözlemlenir.

* ### Kaynaklar
    - https://docs.spring.io/spring-boot/docs/2.1.13.RELEASE/reference/html/boot-features-logging.html
    
[index için tıklayın](../README.md)
