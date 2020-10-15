* ### Referanslar
    Bu çalışmadaki örnekler **com.samples.reflection.sample5.Demo** adlı class ta kodlanmıştır.

* ### package-as-jar projesindeli jar paketleme script'i çalıştırılır
    ```
    $ package-as-jar/src/script.sh
    ```
* ### idea'ya create edilen JAR eklenir.
    Aşağıdaki menüler takip edilerek Run.jar projeye eklenir.
    Project Structure -> Project Settings -> Libraries -> (+) -> Java  
    
* ### JAR özelliklerine erişim
    
    Log class'ının default constructor'unu private olarak tasarlandığı için buradan aşağıdaki komutla instance yaratılamaz.
    ```
    Log log = new Log();
    ```
    instance olmadığı için instance üzerinden class'a erişim de yapılamaz
    ```
    Class aClass = log.getClass();
    ```
    
    paket ve class adı kullanılarak erişim:
    ```
    Class cls = Class.forName("com.jar.Log");
    ```
    
    tanımlı constructorları görüntüle
    ```
    cls.getDeclaredConstructors()
    ```
    
    tanımlı methodları görüntüler
    ```
    cls.getMethods()
    ```
    
    tanımlı fieldları görüntüler
    ```
    cls.getDeclaredFields()
    ```

[index için tıklayın](../README.md)
