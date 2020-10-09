* ### Notlar
    Cahce; ikinci istekte methoddaki return değerinin yeniden hesaplanması yerine memory den cevap verilmesidir.
    EhCache gibi bir cache provider yoksa değerler Spring tarafından HashMap üzerinde tutuluyor.
    
* ### Servis katmanının oluşturulması
    Cache konusu işlenirken <b>Customer</b> üzerinden işlemler yapılacaktır. Dolayısıyla ilk olarak Controller katmanındaki Busssines kodu **CustomerService** adında bir service bean oluşturularak buraya taşınır.
    CustomerService class'ı aşağıdaki şekilde olacaktır.
    ```
    @Service
    public class CustomerService {
        @Autowired
        CustomerRepository customerRepository;
    
        public Customer getCustomerById(Long id) {
            Optional<Customer> optionalCustomer = customerRepository.findById(id);
    
            if (!optionalCustomer.isPresent()) {
                return null;
            }
    
            return optionalCustomer.get();
        }
    }
    ```
    CustomerController class'ı aşağıdaki hale gelecektir.
    ```
    @RestController
    @RequestMapping("customer")
    public class CustomerController {
    
        @Autowired
        CustomerService service;
    
        @GetMapping("/{id}")
        public Customer getCustomerById(@PathVariable Long id) {
            return service.getCustomerById(id);
        }
    }
    ```

* ### Bağımlılıkların eklenmesi
    Spring'in cache ini kullanabilmek için ihtiyaç duyulan **spring-boot-starter-cache** pom.xml'e aşağıdaki gibi eklenir.
    ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-cache</artifactId>
    </dependency>
    ```
    
    Spring birden fazla cache provider destekler. Buradaki örnekte **ehcache** kullanılacaktır. ehcache bağımlılığı pom.xml'e aşağıdaki gibi eklenmelidir. 
    ```
    <dependency>
        <groupId>net.sf.ehcache</groupId>
        <artifactId>ehcache</artifactId>
    </dependency>
    ```
* ### Configurationların yapılması
    Cache'i aktif etmek için springboot uygulamasının başladığı yere aşağıdaki annotation ekenmelidir.
    ```
    @EnableCaching
    ```
* ### Cache'in devreye alınması
    Cache'in devreye alınması için methodun başına **@Cachable** anotasyonu eklenmelidir.
    
    getCustomerById() methodunun son hali aşağıdaki gibi olmalıdır.
    ```
    @Service
    public class CustomerService {
        @Autowired
        CustomerRepository customerRepository;
    
        @Cacheable("cutomerData")
        public Customer getCustomerById(Long id) {
            Optional<Customer> optionalCustomer = customerRepository.findById(id);
    
            if (!optionalCustomer.isPresent()) {
                return null;
            }
    
            return optionalCustomer.get();
        }
    }
    ```    

* ### Logların incelenmesi
    http://localhost:8080/customer/1 adresine ilk gittiğimizde loglarda hibernate'in DB ye sorgu attığını gösteren select loglarını görürüz.
    ```
    Hibernate: 
        select
            customer0_.id as id1_1_0_,
            customer0_.create_date as create_d2_1_0_,
            customer0_.name as name3_1_0_ 
        from
            customers customer0_ 
        where
            customer0_.id=?
    ```
  
  aynı adrese ikinci, üçüncü gidişimizde bu logu göremeyiz. Çünkü artık spring'in cache'i devreye girerek DB ye gitmeden cache ten yanıt vermeye başlamıştır. Zaten cache'in kullanım amacı da budur.

* ### Cachein disable edilmesi.
    Farklı ortamlarda cache'i kullanmak istemeyebiliriz. Örn development ortamında cache disable olsun demek için aşağıdaki parametre application-dev.properties dosyasına eklenmelidir.
    ```
    spring.cache.type=none
    ```

[index için tıklayın](../README.md)
