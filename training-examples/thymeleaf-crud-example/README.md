* ### Referanslar
    Bu çalışmadaki örnekler **thymeleaf-crud-example** adlı projede kodlanmıştır.
    
* ### Açıklamalar
    - Bu çalışmada spring boot ve thymeleaf ile CRUD örneği yapılmıştır.
    - Employee adında bir entity oluşturulmuştur.
    - Uygulama ilk açıldığında tabloların create edilmesi için resources/schema.sql dosyası eklenmiştir.
    - Yine uygulama ilk açıldığında hazır data oluşması için resources/data.sql dosyası ile insert işlemleri yapılmıştır.

* ### Bağımlılıkların eklenmesi
    ilk olarak thymeleaf pom.xml'e eklenmelidir.
    ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    ```
    
    spring boot projesi olacağı için springboot pom.xml'e eklenmelidir.
    ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    ```
    db işlemlerinde h2-database kullılacağı için h2-database pom.xml dosyasına eklenmelidir.
    ```
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
    </dependency>
    ```

* ### thymeleafin Controller katmanından beslenmesi
    model.addAttribute("employees", list) ile thymeleaf te kullanılacak employes değişkeni model'e eklenmiş olur.
    ```
    @RequestMapping
    public String getAllEmployees(Model model) {
        List<Employee> list = service.getAllEmployees();
    
        model.addAttribute("employees", list);
        return "list-employees";
    }
    ```

    thymeleaf tarafında ise model attribute ü ${employees} olarak kullanılır.
    ```
    <div th:switch="${employees}" class="container my-5">
    ...
    </div>
    ```    

* ### thymeleaf ile html dosyalarının hazırlanması
    thymeleaf ile kullanılacak html dosyaları resources/templates dizininde oluşturulmuştur.
    
    th:action="@{/createEmployee}" ile formun hangi endpointe POST edleceği belirtilir.
    ```
    <form action="#" th:action="@{/createEmployee}" th:object="${employee}" method="post">
    ```
    
    th:field="*{firstName}" ile formun fieldları thymeleaf üzerinde işaretlenir.
    ```
    <input type="text" th:field="*{firstName}" class="form-control"
           id="firstName" placeholder="Ad"/>
    ```
    
    th:switch="${employees}" ile thymeleaf üzerinde bir switch yapısı oluşturulur.
    ```
    <div th:switch="${employees}" class="container my-5">
    ```
    
    Oluşturulan swithc yapısı aşağıdkai şekilde kullanılır.
    ```
    <h5 th:case="null">Kayıt bulunamadı!!</h5>
    <div th:case="*">
    ...
    </div>
    ```
    
    Edit işlemi için kullanılacak endpoint : @{/edit/{id}(id=${employee.id})}
    ```
    <a th:href="@{/edit/{id}(id=${employee.id})}" class="btn btn-warning">
        <i class="fas fa-user-edit ml-2"></i>
    </a>
    ```
    
    Delete işlemi için kullanılacak endpoint
    ```
    <a th:href="@{/delete/{id}(id=${employee.id})}" class="btn btn-danger">
        <i class="fas fa-user-times ml-2"></i>
    </a>
    ```

* ### Kaynaklar
    https://howtodoinjava.com/spring-boot2/crud-application-thymeleaf/ 

[index için tıklayın](../README.md)
