* ### Referanslar
    - Bu çalışmadaki örnekler **com.ba.controller.CarCustomerController** adlı class ta kodlanmıştır.
    
* ### Açıklamalar
    - Ardarda gerçekleşen SQL işlemlerinin bir bütün halinde ele alınması işlemidir.
    - Bu ardışık işlemlerden hernagi birinde hata oluşursa bütün halinde tüm veriler geri(**rollback**) alınır.
    - Eğer tüm ardışık işlemler başarılı bir şekilde gerçekleşmişse bu durumda değişiklikler veritabanına **commitlenir**.
    - Springte temel transaction işlemleri **PlatformTransactionManager** ile gerçekleşir.
    - PlatformTransactionManager farklı platformlar üzerinde aynı kod ile transaction işlemleri gerçekleşebilir.
    ÖRN: SpringDataJpa yerine Direk JDBC kullanılması.
    - Genellikle Transaction Demarcation işlemi @Transactional annotasyonu ile  deklaratif olarak yapılır. 
    - Ancak spring ile  programatik olarak transaction'u başlatıp bitirmek mümkündür.

* ### Propagation
    - **MANDATORY** : Daha önceden başlatılmış bir transaction beklenir. Eğer bu method çalışmaya başladığı anda mevcut bir transaction yoksa exception fırlatılır.
    - **NESTED** : REQUIRES_NEW ile aynıdır(for JDBC)
    - **NEVER** : Methodun transactionsız bir ortamda çalışacağı anlamına gelir. Eğer methodun çalıştığı anda mevcutta bir transaction varsa Exception fırlatılır.
    - **NOT_SUPPORTED** : Mevcutta bir transaction varsa bile method transaction dışında çalıştırılır.
    - **REQUIRED** : @Transactional'ın default tanımıdır. Transaction yoksa bir transaction açılarak ilgili method bu transaction içinde  çalıştırılır.
        ```
        @Transactional(propagation = Propagation.REQUIRED)//default
        ```
    - **REQUIRES_NEW** : İşlemler yeni bir transaction olarak uygulanır. Farklı bir trasnactional method içinen çağrılıyor olsa bile iki transaction arasında bir bağ yoktur.
    - **SUPPORTS** : Aktif transaction varsa method işlemleri bu transaction içinde gerçekleşir. Transaction yoksa method direkt olarak(transaction sız) çalışır.

* ### Notlar
    - NOT1 : Springte JPA-Hibernate kullanılarak DB işlemleri gerçekleştiriliyorsa transaction işlemleri esnasında bütün PersistanceContext taranır ve üzerinde değişiklik gerçekleşen tüm entityler DB ye commitlenir.
    Dolayısıyla sadece sorgulama yapan DB isteklerindeki transactionların readOnly olarak işaretlenerek bu maliyet düşürülebilir.
    ```
    @Transactional(readOnly = true)
    ```
    - Spring'in kendi @Transactional annotasyonu javax paketinin altında gelenden daha kabiliyetlidir.   
       
    - Defaullta sadece **RuntimeException(unchecked)** oluşması durumunda rollback yapılır. Checked exception atılması durumunda da Rollback yapması için aşağıdaki tanım kullanılır.
    ```
    @Transactional(rollbackFor = Exception.class)
    ```

* ### Kaynaklar
    - https://www.marcobehler.com/guides/spring-transaction-management-transactional-in-depth 
    - https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/transaction/PlatformTransactionManager.html

[index için tıklayın](../README.md)
