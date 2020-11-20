* ### Referanslar
    Bu çalışmadaki örnekler **spring-basic-auth-example** adlı projede  kodlanmıştır.
    
* ### User işlemleri için gereken Admin rolünün eklenmesi
    Aşağıdaki tanımlamayla **/user/**** endpointlerine gelen kullanıcıda **ADMIN** rolü arandığı belirtilmiştir.
    ```
    http.authorizeRequests().antMatchers("/user/**").access("hasRole('ADMIN')");
    ```
    tanımlama **com.ba.config.SecurityConfig** classında yapılmıştır.
  
* ### User işlemleri için gereken endpointlerin eklenmesi
    Mevcuttaki **users** ve **authorities** tablolarına yetkilendirilmiş kullanıcı eklemek için **http://localhost:8080/user/add/ahmet/123** şeklinde bir endpoint tanımlanmıştır.
    
    ```
    @RestController
    @RequestMapping("/user")
    public class UserController {
    
        @Autowired
        private UserRepository userRepository;
    
        @Autowired
        private AuthorityRepository authorityRepository;
    
        @GetMapping("/add/{username}/{password}")
        public String addUser(@PathVariable String username, @PathVariable String password) {
    
            User user = new User();
            user.setEnabled(true);
            user.setPassword("{noop}" + password);
            user.setUsername(username);
    
            userRepository.save(user);
    
            Authorities authorities = new Authorities();
            authorities.setAuthority("ROLE_USER");
            authorities.setUsername(username);
    
            authorityRepository.save(authorities);
    
            return "user created";
        }
    
    }
    ```
    Buradaki örnekte **User** ve **Authorities** entityleri doldurulup repository üzerinden save edilmiştir.

* ### Entitylerin eklenmesi

    Authorities tablosuna karşılık gelen entity aşağıdaki şekilde tanımlanmaktadır.
    ```
    @Entity
    @Table(name = "authorities")
    public class Authorities {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String username;
        private String authority;
    ...
    }
    ```

    **JPA, entityler içinde defaultta bir ID alanı beklediği için tabloya Long tipinde id alanı eklenmiştir.**
    ```
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    ```

    users tablosuna karşılık gelen entity aşağıdaki şekilde tanımlanmalıdır.
    ```
    @Entity
    @Table(name = "users")
    public class User {
    
        @Id
        @Column(name = "username",nullable = false)
        private String username;
    
        private String password;
    
        private boolean enabled; 
    ...
    }
    ```

###### NOTE : Users tablosunde username zaten unique bir alan olduğu için entity e yeni bir ID alanı eklemeden username alanı @Id olarak işaretlenmiştir.

* ### SQL scriptlerinin güncellenmesi
    ID alanının AUTHORITIES tablosuna eklenmesi
    ```
    ID BIGINT NOT NULL AUTO_INCREMENT,
    ```
    
    ```
    CREATE TABLE AUTHORITIES (
      USERNAME VARCHAR(250) NOT NULL,
      AUTHORITY VARCHAR(250) NOT NULL
        ID BIGINT NOT NULL AUTO_INCREMENT,
        USERNAME VARCHAR(250) NOT NULL,
        AUTHORITY VARCHAR(250) NOT NULL,
        PRIMARY KEY (ID)
    );
    ```

    Insert scriptlerine ID alanının eklenmesi
    ```
    INSERT INTO AUTHORITIES VALUES (1,'user1','ROLE_USER');
    INSERT INTO AUTHORITIES VALUES (2,'user2','ROLE_USER');
    INSERT INTO AUTHORITIES VALUES (3,'user2','ROLE_ADMIN');
    INSERT INTO AUTHORITIES VALUES (4,'admin','ROLE_ADMIN');
    ```

* ### Repository katmanlarının eklenmesi
    UserRepository
    ```
    public interface UserRepository extends JpaRepository<User, String> {...}
    ```
    
    AuthorityRepository
    ```
    public interface AuthorityRepository extends JpaRepository<Authorities, Long> {...}
    ```

* ### Endpointlere erişim
    - User ekleme işlemini yapan HTTP requesti aşağıdaki gibidir.
    ```
    curl --location --request GET 'http://localhost:8080/user/add/ahmet/123' --header 'Authorization: Basic YWRtaW46cGFzczM='
    ```    

[index için tıklayın](../README.md)
