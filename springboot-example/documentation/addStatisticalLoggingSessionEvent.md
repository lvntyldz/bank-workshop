* ### application.properties dosyasının güncellenmesi
    Uygulamada hibernate performansını izlemek ve detayları logda gözlemlemek için aşağıdaki parametreler application.properteis dosyasına eklenir.
    ```
    spring.jpa.properties.hibernate.generate_statistics=true
    logging.level.org.hibernate.stat=debug
    ```

    <b>logging.level.org.hibernate.stat : </b> log seviyesi ayarlanır. <br/>
    <b>spring.jpa.properties.hibernate.generate_statistics : </b> istatistik loglarını açıp kapatır


* ### log mesajlarının incelenmesi
    uygulama ayağa kaldırıldıktan sonra DB işlemlerinin ardından loglara bakıldığında aşağıdaki gibi sorgu maliyetlerini içeren detaylı bir bilgi logu görülür.
    ```
    2020-10-06 19:42:43.683  INFO 17394 --- [nio-8080-exec-2] i.StatisticalLoggingSessionEventListener : Session Metrics {
    29260 nanoseconds spent acquiring 1 JDBC connections;
    0 nanoseconds spent releasing 0 JDBC connections;
    29343 nanoseconds spent preparing 1 JDBC statements;
    279222 nanoseconds spent executing 1 JDBC statements;
    0 nanoseconds spent executing 0 JDBC batches;
    0 nanoseconds spent performing 0 L2C puts;
    0 nanoseconds spent performing 0 L2C hits;
    0 nanoseconds spent performing 0 L2C misses;
    132443 nanoseconds spent executing 1 flushes (flushing a total of 1 entities and 0 collections);
    0 nanoseconds spent executing 0 partial-flushes (flushing a total of 0 entities and 0 collections)
    }
    ```

    ```
    29260 nanoseconds spent acquiring 1 JDBC connections;
    ```
    
    ```
    132443 nanoseconds spent executing 1 flushes (flushing a total of 1 entities and 0 collections);
    ```

[index için tıklayın](../README.md)
