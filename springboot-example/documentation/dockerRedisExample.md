* ### açıklamalar
    - Redis bir key-value database dir.
    - spring ile birlikte kullanılabilir.
    - key-value ve distributed(multi node cluster) özelliğinden dolayı cache server olarak kullanılabilir.

* ### docker ile redis imageının indirilmesi
    Redis imageının son verisyonunu dockerhubtan almak için aşğıdaki komut çalıştırılır.
    ```
    $ docker run --name my-redis-container  -d redis
    ```

* ### çalışan docker contanerları
    Çalışan tüm docker containerlarını listelemeke için aşağıdaki komut çalıştırılır.
    ```
    $ docker ps
    ```

* ### redis'in 6379 nolu portta çalışması
    redis'in istenilen portta continerın dışına çılabilmesi için -p parametresiyle aşağıdaki komut çalıştırılır.
    ```
    $ docker run -p 6379:6379 redis
    ```

* ### redis'e ssh ile bağlantı
    Docker exec komutuyla ayağa kalkan redis container'ına ssh connectionı yapılır.
    ```
    $ docker exec -it my-redis-container  sh
    ```
  
* ### redis kurulumunun kontrol edilmesi
    Redis cli'ın ayakta olup olmadığını görmek için aşağıdaki komut ile redis-cli'a ping atılır.
    ```
    $ redis-cli ping
    ```

* ### redis-cli' bağlantı
    
    ```
    $ redis-cli
    ```

* ### redis'e key ekleme
    Redis üzerinde key-value eklemek için set komutu aşağıdaki gibi kullanılabilir
    ```
    $ set firstname levent 
    ```

* ### redis'e eklenen key'i görüntüleme
    Redisteki key leri görüntülemek için aşağıdaki gibi get <KEY_NAME> şeklinde komutu çalıştırılır. 
    ```
    $ get firstname
    output : 'levent'
    ```

* ### telnet ile redise bağlantı
    Kurulan redis sunucusuna ip ve port bilgisiyle birlikte telenet ile bağlanılabilir.
    ```
    $ telnet 127.0.0.1 6379
    ```

[index için tıklayın](../README.md)
