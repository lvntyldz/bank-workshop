* ### Referanslar
    - Bu çalışmadaki örnekler **spring-auth-custom-user-detail-sservice** adlı projede  kodlanmıştır.

* ### Açıklamalar
    - Çalışmada spring securty nin basic authentication da beklediği users ve authorities tabloları yerine farklı user ve role tablolarının nasıl kullanılacağı gösterilmiştir.  
    - Spring, **@Bean** anotasyonuyla işaretlenen metodların return tipindeki objeleri **ApplicationContexte** bean olarak ekler.
    ```
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
    ```

* ### Entitylerin oluşturulması ve ManyToMany ilişkinin eklenmesi
    - User ve Role bilgilerinin tutulacağı iki adet entity oluşturulur.
    - User
    ```
    @Entity
    @Table(name = "USERS")
    public class User {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    
        private String email;
    
        private String username;
    
        private String password;
    
        private boolean enabled;
    
        @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        @JoinTable(
                name = "USER_ROLES",
                joinColumns = @JoinColumn(name = "USER_ID"),
                inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
        )
        private Set<Role> roles = new HashSet<>();
        
        ...
    }
    ```
    
    - User ve Role arasındaki ManyToMany ilişki
    ```
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private Set<Role> roles = new HashSet<>();
    ```
    
    - Role
    ```
    @Entity
    @Table(name = "ROLES")
    public class Role {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    
        private String name;
        ...
    }
    ```

* ### Repositorylerin oluşturulması
    - RoleRepository
    ```
    public interface RoleRepository extends JpaRepository<Role, Long> {...}
    ```
    
    - UserRepository
    ```
    public interface UserRepository extends JpaRepository<User, Long> {
    
        @Query("SELECT u FROM User u WHERE u.username = :username")
        User getUserByUsername(@Param("username") String username);
    }
    ```
    - **NOT :** 
    Girilen username bilgisine göre User entity'sini dönen bir method oluşturulur.
    @Query anotasyonu ile username e göre User bilgileri çekilir.

* ### Tabloların oluşturulması
    - USERS tablosu
    ```
    CREATE TABLE USERS (
        ID INT(11) NOT NULL AUTO_INCREMENT,
        EMAIL VARCHAR(45) ,
        USERNAME VARCHAR(45) NOT NULL,
        PASSWORD VARCHAR(64) NOT NULL,
        ENABLED TINYINT(4) DEFAULT NULL,
        PRIMARY KEY (ID),
        UNIQUE KEY EMAIL_UNIQUE (EMAIL)
    );
    ```
    
    - ROLES tablosu
    ```
    
    CREATE TABLE ROLES (
        ID INT(11) NOT NULL AUTO_INCREMENT,
        NAME VARCHAR(45) NOT NULL,
        PRIMARY KEY (ID)
    );
    ```
    
    - USER_ROLES tablosu
    ```
    CREATE TABLE USER_ROLES (
        USER_ID INT(11) NOT NULL,
        ROLE_ID INT(11) NOT NULL,
        CONSTRAINT ROLE_FK FOREIGN KEY (ROLE_ID) REFERENCES ROLES (ID),
        CONSTRAINT USER_FK FOREIGN KEY (USER_ID) REFERENCES USERS (ID)
    );
    ```

* ### Default user ve passwordün insert edilmesi
    - insert Users
    ```
    INSERT INTO USERS (email,username, password, enabled)VALUES ('aa@bb.cc','admin','$2a$10$soDKnoTm4BkaaRCFQkldsOCZDfCTSc0NBn0XvH9CL6nssg3UrB0Rm',1); -- u:admin p:123
    ```
    
    - insert Roles
    ```
    INSERT INTO ROLES (ID,NAME) VALUES (1,'ROLE_USER');
    INSERT INTO ROLES (ID,NAME) VALUES (2,'ROLE_ADMIN');
    ```
    
    - User ve Role ilişkisinin insert edilmesi
    ```
    INSERT INTO USER_ROLES (USER_ID,ROLE_ID) VALUES (1,1);
    INSERT INTO USER_ROLES (USER_ID,ROLE_ID) VALUES (1,2);
    ```
  
* ### UserDetailsService'in implement edilmesi
    - Spring security içindeki credentials işlemlerinde kullanılan UserDetailsService implement edilmelidir.
    ```
    org.springframework.security.core.userdetails.UserDetailsService;
    ```
    
    - Implementasyon esnasında  yine spring security içinde auth işlemleri için kullanılan  UserDetails tipinde bir değer dönen loadUserByUsername() methodu override edilmelirdir.
    ```
    org.springframework.security.core.userdetails.UserDetails;
    ```
    
    - override edilen loadUserByUsername() methodunun içeriği
    ```
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
    
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
    
        return new UserDetailsImpl(user);
    }
    ```
    - Buradaki kodda repositoryden çekilen User entitysi alınarak  UserDetails'in implemenrasyonu olan UserDetailsImpl class'ına verilir.
    - UserDetailsImpl class'ı yaratılırken UserDetails ten implement edildiği için bu interfaceten gelen methodlar override edilmelidir. 
    

* ### UserDetails'in implement edilmesi
    - UserDetailsIml class'ı UserDetails ten implement edilir.
    ```
    public class UserDetailsImpl implements UserDetails {...}
    ```
    
    - Interfaceden gelen(UserDetails) bütün methodlar @Override edilir.
    ```
    ...
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
    ...
    ```
    
    - GrantedAuthority den extends edilecek bir değer dönecek olan getAuthorities() methodunda user'ın rollerine erişilerek authorities bilgisi spring'in istediği formata(GrantedAuthority) dönüştürülür. 
    ```
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
    
        return authorities;
    }
    ```
    - Şifre kontrolünde kullanılan getPassword() methodunun override edilerek User classından(entity) alınan passwordun return edilmesi
    ```
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    ```    
    
* ### Password Encoder'un belirlenmesi
    - Varsayılanda spring security NoOpPasswordEncoder kullanır. Yani şifreler açık bir şekilde DB ye yazılır.
    - Varsayılan encoder dışında farklı encoderlar kullanılarak ta şifreler encript edilebilir.  
    - Çalışmada BCryptPasswordEncoder kullanılarak şifrelerin encript edileceği Authentication Provider'a söylenmiştir.
    ```
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    ```
    
    - password encoder'un AuthenticationProvider'a bildirilmesi
    ```
    authProvider.setPasswordEncoder(passwordEncoder());
    ```

* ### DaoAuthenticationProvider'ın parametrelerinin verilmesi
    - DaoAuthenticationProvider bean'inin istenen bilgilerle(userDetailService ve passwordEncoder)  oluşturulması
    ```
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
    
        return authProvider;
    }
    ```

* ### AuthenticationProvider'ın set edilmesi
    - Çalışmada artık default authenticationProvider kullanılmadığı için üstte bilgileri set edlen DaoAuthenticationProvider'ın AuthenticationManagerBuilder'a set edilmesi gerekmektedir.
    ```
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    ```

* ### HttpSecurity konfigurasyonu
    - Bu kısım daha önce **Spring Booot Basic Auth örneği** [doküman için tıklayın](../spring-basic-auth-example/README.md) başılığı altında anlatılmıştır.
    - Burada  user endpointlerine sadece adminin erişebileceği bir konfigürasyon tanımı yapılmıştır.
    ```
    http.authorizeRequests().antMatchers("/user/**").access("hasRole('ADMIN')");
    ```
    
    - Kofigürasyonun tamamı
    ```
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("h2-console/**").permitAll();
        http.csrf().disable();//disable csrf
        http.headers().frameOptions().disable();//disable frame options
        http.authorizeRequests().antMatchers("/login/**").permitAll();
        http.authorizeRequests().antMatchers("/user/**").access("hasRole('ADMIN')");
        http.httpBasic();
    }
    ```
    
* ### Endpoint lere erişim
    - Authentication yaparak kullanıcı ekleme (u:admin p:123)
    ```
    curl --location --request GET 'http://localhost:8080/user/add' \
    --header 'Content-Type: application/json' \
    --header 'Authorization: Basic YWRtaW46MTIz' 
    ```    
    
    - Authentication yaparak kullanıcı güncelleme (u:admin p:123)
    ```
    curl --location --request GET 'http://localhost:8080/user/update' \
    --header 'Content-Type: application/json' \
    --header 'Authorization: Basic YWRtaW46MTIz' 
    ```    
    
    - Authentication yaparak kullanıcıları listeleme (u:admin p:123)
    ```
    curl --location --request GET 'http://localhost:8080/user/list' \
    --header 'Content-Type: application/json' \
    --header 'Authorization: Basic YWRtaW46MTIz' 
    ```    
    
    - Authentication yaparak rolleri listeleme (u:admin p:123)
    ```
    curl --location --request GET 'http://localhost:8080/user/list-roles' \
    --header 'Content-Type: application/json' \
    --header 'Authorization: Basic YWRtaW46MTIz' 
    ```    

* ### Kaynaklar
    - https://www.codejava.net/frameworks/spring-boot/spring-boot-security-role-based-authorization-tutorial 

[index için tıklayın](../README.md)
    
