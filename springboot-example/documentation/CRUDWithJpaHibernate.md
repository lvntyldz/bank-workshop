* ### Bağımlılıkların(dependency) eklenmesi
    hibernate ve jpa yı içeren spring-boot-starter-data-jpa pom.xml dosyasına aşağıdaki şekilde eklenir
    ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    ```

    h2 veritabanı bağımlılığı runTime da kullanabilmesi için aşağıdaki şekilde pom.xml dosyasına eklenir
    ```
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    ```

* ### H2 console(Web) aktif edilmesi
    h2 veritabanını web üzerinden erişime açmak için application.properties dosyasına aşağıdaki parametre eklenmelidir.
    ```
    spring.h2.console.enabled=true
    ```
    
    Ekleme işleminden sonra aşağıdaki adresten h2 DB'ye bağlanılarak DB işlemleri console üzerinden yapılabilir.
    <br/> URL: http://localhost:8080/h2-console/
    
    ###### NOT: varsayılan connection ayarları aşağıdaki gibidir
    Driver Class : org.h2.Driver <br/>
    JDBC URL:jdbc:h2:mem:testdb <br/>
    User Name:	sa <br/>


* ### Entity oluşturulması
    Veritabanıyla iletişimde kullanılacak Student adına bir entity oluşturulur.
    ```
    @Entity
    public class Student {
      @Id
      @GeneratedValue
      private Long id;
      private String name;
      private String passportNumber;
      ...
    }
    ```
    
* ### Repository oluşturulması
    DB işlemlerinin otomotik yapılabilmesi için JpaRepository den extends edilen bir StundetRepository interface'i oluşturulur.
    ```
    @Repository
    public interface StudentRepository extends JpaRepository<Student, Long>{    
    }  
    ```
* ### Controller(endpoint) oluşturulması
    İlgili requestlerin MAP edilebilmesi için bir controller katmanı oluşturulur. Burada StudentRepository @Autowired ile Inject edilir.
    ```
    @RestController
    @RequestMapping("student")
    public class StudentController {
    
        @Autowired
        StudentRepository repository;
        ...
    }
    ```
  
    Student adlı Entity den bir instance yaratılarak inject edilen StudentRepository üzerinden save edilir.
    ```
    Student student = new Student(null, "ali", "123");
    repository.save(student);
    ``` 
    
* ### Application.properties dosyasının güncellenmesi
    DB operasyonlarıyla birebir ilişkili aşağıdaki parametreler application.properties dosyasına eklenmelidir. 
    ```
    # Show all queries
    spring.jpa.hibernate.ddl-auto=create-drop
    spring.datasource.url=jdbc:h2:mem:testdb
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
    logging.level.org.hibernate.type=trace
    ```
  
    <b>spring.jpa.hibernate.ddl-auto : </b> hibernate'in ayağa kalkarken entitylerdeki değişiklikleri DB ile eşleştirmesini sağlar. <br/> 
    <b>spring.datasource.url : </b> Bağlanılacak DB'nin IP adresi ve PORT bilgisini içerir <br/>
    <b>spring.jpa.show-sql : </b> Hibernate'in Java nesnelerini DB ye vururken çalıştırdığı SQL leri görüntüler. Loglama ve analizde önemi yüksektir. <br/> 
    <b>spring.jpa.properties.hibernate.format_sql : </b> Hibernate sorgularının formatlanmış olarak gösterimini sağlar <br/>
    <b>logging.level.org.hibernate.type=trace : </b> hibernate.type log level parametresidir. Hibernate ile SQL cümleciğine dönüşüm sağlandıktan sonra bu SQL cümleciğine gönderilen parametreleri yazdırmak için kullanılır. <br/> 
   
   <b>Öğrenci eklemek için : </b> http://localhost:8080/student/add <br/>
   <b>Öğrenci listelemek için : </b> http://localhost:8080/student/list <br/>
   <b>Öğrenci silmek için : </b> 
    ```
   curl --location --request DELETE 'http://localhost:8080/student/delete/1'
    ```
   
[index için tıklayın](../README.md)
