* ### Referanslar
    Bu çalışmadaki örnekler **com.ba.controller.CustomerController** adlı class ta kodlanmıştır.
    
* ### Yeni kayıt oluşturma 
    Cache işlemlerinin örneklendirilmesi için ID:5 olan bir Customer DB'ye eklenir.
    ```
    INSERT INTO CUSTOMERS (ID, NAME, CREATE_DATE) VALUES (5, 'Yılmaz',  CURRENT_TIMESTAMP);
    ```    

* ### Cache'e yazma 
    Springte cache'e yazma işlemi **@Cacheable** anotasyonu ile yapılır. Bu metod ilk çağrıldığında çalışır ancak soraki isteklerde Spring'in cache'inden yanıt verilir. Dolayısıyla ilk istekten sonraki isteklerde methodun gövdesi çalıştırılmaz.
    ```
    @Cacheable(value = "CustomerCache", key = "'CUSTOMER_CAHE_BY_ID_'.concat(#id)", condition = "#id >= 5")
    ```
  
    **NOTE1:** condition = "#id >= 5" ifadesiyle sadece ID'si 5 ve 5 ten büyük olan Customer verileri cachelenecektir.  
    **NOTE2:** id:5 olan Customer objesi için  **key = "'CUSTOMER_CAHE_BY_ID_'.concat(#id)"** ifadesiyle oluşturulan cacheKey **CUSTOMER_CAHE_BY_ID_5** şeklindedir.  

* ### Cache'i güncelleme  
    Springte cache güncelleme **@CachePut** anotasyonuyla yapılır. @Cacheable dan farkı method her çağrıldığında methodun gövdesi çalışır.
    ```
    @CachePut(value = "CustomerCache", key = "'CUSTOMER_CAHE_BY_ID_'.concat(#id)", condition = "#id >= 5")
    ```

* ### Cache ten silme 
    Cacheten silme  @CacheEvict ile yapılır. 
    ```
    @CacheEvict(value = "CustomerCache", key = "'CUSTOMER_CAHE_BY_ID_'.concat(#id)", condition = "#id >= 5")
    ```    
  
* ### Tüm cache'i temizleme
    **@CacheEvict** anotasyonu ile **value = "CustomerCache"** olan tüm veriler aşağıdaki şekilde cachten silinir.
    ```
    @CacheEvict(value = "CustomerCache", allEntries = true)
    ```

* ### Web üzerinden end-pointlere erişim
    - **id:2 olan(cache'e yazılmayan) kaydın görüntülenmesi** http://localhost:8080/customer/2
    - **id:5 olan kaydın görüntülenmesi** http://localhost:8080/customer/5
    - **id:5 olan müşterinin adını mert olarak güncelleme** http://localhost:8080/customer/5/mert
    - **id:5 olan kaydı silme** 
        ```
        curl --location --request DELETE 'http://localhost:8080/customer/delete/5'
        ```
    - **tüm kayıtları silme**
        ```
        curl --location --request DELETE 'http://localhost:8080/customer/delete/all'
        ```    

* ### NOTLAR
    - 1 id:5 olan kayıt URL den listelenip ardından kaydın adı mert olarak değiştirilmesi sırasında **@CachePut** ile cache güncellemesi yapılmasaydı id:5 olan kayıt tekrar listelendiğinde cacheten eski veri dönülecekti. 
    - 2 id:5 olan kayıt URL den listelenip ardından kaydın silinmesi sırasında **@CacheEvict** ile cache temizleme yapılmasaydı id:5 olan kayıt tekrar listelendiğinde **DB de olmamasına rağmen cacheten eski veri dönülecekti**. 
   
[index için tıklayın](../README.md)
