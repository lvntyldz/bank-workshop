* ### Referanslar
    - Bu çalışmadaki örnekler **com.ba.controller.BeanInfoController** adlı classta  kodlanmıştır.
    - Çalışmada spring beanlerine dinamik olarak erişim amaçlanmıştır.
    - Çalışmada Springin içindeki **ApplicationContext** ten beanlerin nasıl çekileceği gösterilmiştir.

* ### Açıklamalar
    - Spring annotationları ile yaratılan beanler application contextte tutulur.
    - **@Component, @Service, @Controller,...** anotasyonlarını classların üstüne ekleyince  bu classlar birere spring bean'i olarak işaretlenmiş olur.
    - Yaratılan beanlerin listesine endpointten ulaşmak için **acuatorler(spring-boot-starter-actuator)** kullanılabilir
    - Programatically bir şekilde de beanlere erişilebilir.
    - Bazen dinamik olarak ihtiyacımız olan beanler üzerinde işlem yapmak isteriz.
    - **ÖRN:** Product güncelleme işleminde **Product'ta onlarca column varsa DTO dan Entity classlara dönüştürme** işlemi esnasında bütün alanlarının değilde sadece değişen alanlar üzerinde işlem yapılmasını isteriz.
    Bu durumda her bir alan için birere converter bean yaratılır. Daha sonra sadece değişiklik yapılmış alanların Converter beanleri Spring context ten alınarak üzerinde işlem yapılabilir.  
    **ProductTitleConverter, ProductDiscountConverter, ProductSellerConverter,...** şeklinde tanımlanan beanlerden  sadece discount converter'ı çekerek işlem yapılabilir.
        
* ### Endpointler
    - **Tüm bean listesini dönen endpoint :** http://localhost:8080/beans/all 
    Bu adrese gidilerek daha önce oluşturlan beanlerin isimlerinin listede olup olmadığı kontrol edilebilir.
    Projede daha önceki örnekler yapılırken oluşturulan beanler den bazıları aşağıdadır
    
    - bookController bean
    ```
    @RestController
    @RequestMapping("book")
    public class BookController {...}
    ```
        
    - employeeRepository bean        
    ```
    @Repository
    public interface EmployeeRepository extends JpaRepository<Employee, Long> {...}
    ```
    
    - personService bean    
    ```
    @Service
    public class PersonService {...}
    ```
        
    - todoService bean    
    ```
    @Service
    public class TodoService {...}
    ```

    - **İsme göre bean veren endpoint :**  http://localhost:8080/beans/by-name
    Buradaki endpointte **TodoService** **ApplicationContext** ten alınarak todoService içindeki **addNewTodo()** methodu çağrılmıştır. 
    ```
    TodoService todoService = (TodoService) context.getBean("todoService");
    todoService.addNewTodo();
    ```    



[index için tıklayın](../README.md)
