##### application.properties
- uygulamanın ayar dosyasıdır.
- varsayılan olarak set edilmiş çok sayıda configurasyon(port numarası gibi) buradan değiştirilebilir.
- yeni config parametreleri buradan eklenebilir.
- application-<profil>.properties dosyası application.properties dosyasını extens eder. aynı tanımların tekrar yapılmasına gerek yoktur. 
- application-<profil>.properties şekline N tane farklı profil tanımlanabilir.

* ##### prod 
    spring profil bilgisinin parametre olarak geçilmesi
    ```
    -Dspring.profiles.active=<PROFILE_NAME>
    ```

    prod profiliyle uygulamanın ayağa kaldırılması. 
    Burada uygulama class path deki application-prod.properties(application-<PROFILE_NAME>.properties) dosyasını okuyarak buradaki configurasyonlarla ayağa kalkar.
    ```
    $ mvn clean install && java -jar -Dspring.profiles.active=prod   ./target/springboot-example-1.0.jar
    ```

    ###### NOT: burada application-prod.properites içinde server.port tanımı olmadığı için direk application.properties dosyasındaki değer(server.port=8080) okunur ve uygulama 8080 de ayağa kalkar.


* ##### dev 
    dev profiliyle uygulamanın ayağa kaldırılması. 
    Burada uygulama class path deki application-dev.properties dosyasını okuyarak buradaki configurasyonlarla ayağa kalkar.
    ```
    $ mvn clean install && java -jar -Dspring.profiles.active=dev   ./target/springboot-example-1.0.jar
    ```
    ###### NOT: burada application-dev.properites içinde server.port tanımli olduğu için  application.properties dosyasındaki değer(server.port=8080) override edilir ve uygulama 8090 da ayağa kalkar.

    ###### NOT: profil değişikliklerinden sonra mvn clean unutulmamalıdır.



[index için tıklayın](../README.md)
