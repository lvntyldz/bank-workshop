* ### Referanslar
    Bu çalışmadaki örnekler **com.ba.controller.AlbumController** adlı class ta kodlanmıştır.
    
* ### Bağımlılıkların eklenmesi
    - pom.xml dosyasına spring validation aşağıdaki şekilde eklenir.
    ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    ```

* ### Tabloların oluşturulması
    - Bu çalışmada Album adında bir entity ve bu entity ile ilişkili olarak TBL_ALBUM adında bir tablo oluşturulmuştur.
    ```
    CREATE TABLE TBL_ALBUM (
        ID BIGINT NOT NULL AUTO_INCREMENT,
        TITLE VARCHAR(128),
        URL VARCHAR(128),
        YEAR BIGINT NOT NULL,
        ACTIVE NUMBER(1),
        PRIMARY KEY (ID)
    );
    ```

* ### Validasyon kurallarının DTO'ya eklenmesi
    - Controller kısmında istekleri direk Entity ile karşılamak yerine DTO ile karşılandığı için bu seviyede validasyon DTO içinde yapılır.
    - DTO içinde **javax.validation.constraints** paketiyle gelen annotasyonlar kullanılarak kurallar oluşturulur.
    ```
    public class AlbumDTO {
      ....
      @Min(value = 1900, message = "Yıl bilgisi 1900'den küçük olamaz")
      @Max(value = 2020, message = "Yıl bilgisi 2020'den büyük olamaz")
      @NotNull(message = "Yıl alanı zorunludur")
      private int year;
      ...
    }
    ```

* ### Validasyon kurallarının Entity classlara eklenmesi
    - Büyük projelerde bazen entityler iç classlarda oluşturlur(instance).
    - Bu tür DTO validasyonundan geçemeden direk entity instance'ı yaratılıp DB'ye vuruluyorsa DB tarafında da bir validasyon kuralı oluşturulmalıdır.
    ```
    @Entity
    @Table(name = "TBL_ALBUM")
    public class Album {
      ...
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Min(value = 1, message = "ID 0'dan büyük olmalıdır")
      private Long id;
      ...
    }
    ```

* ### Controller da DTO doğrulama
    - Controller seviyesinde requestleri karşılarken doğrulama yapmak için **@Valid** annotasyonu kullanılır.
    ```
      @PostMapping
      public Album addNewAlbum(@Valid @RequestBody AlbumDTO dto) {
        return service.addNewAlbum(dto);
      }
    ```
    
    - Controller katamından atılan validation exceptionları **ResponseEntityExceptionHandler** classı içinden gelen **handleMethodArgumentNotValid** methodu override edilerek handle edilebilir.
    ```
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    ErrorResponseDTO response =
        new ErrorResponseDTO(
            new Date(),
            "Validation Failed",
            ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
    ```

* ### Entity doğrulama
    - Entity içinde **javax.validation.constraints** paketiyle gelen anotasyonlar kullanıldığı zaman Jpa buradaki kuralları işeterek istenenin dışında bir tanım varsa exception atar.
    - Bu seviyede yapılan doğrulamada **ConstraintViolationException** atılır.  
    - Burada oluşan exceptionlar aşağıdaki şekilde handle edilir.
    ```
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        ErrorResponseDTO response = new ErrorResponseDTO(
                new Date(),
                "Validation Failed",
                ex.getConstraintViolations().iterator().next().getMessageTemplate());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
    ```

* ### Notlar
    - spring validation pom.xml'e(classpath) eklendiyse default olarak aktif gelir. 
    - Bu durumda spring **javax.validation.constraints** paketiyle gelen/@valid olarak işaretlenen tüm alanları kontrol eder. 
    - Validation persistance(entity) seviyesinde disable edilmek istenirse aşağıadki parametre ile application.properties dosyasından kapatılabilir.
    ```
    spring.jpa.properties.javax.persistence.validation.mode=none
    ```

* ### Endpointler
- Hibernate ile persistance seviyesinde ID kontrolü(id<0)
```
curl --location --request POST 'localhost:8080/albums' \
--header 'Content-Type: application/json' \
--data-raw '{
  "id": -123,
  "title": "",
  "url": "http",
  "year": 2020,
  "active":false
}'
```
- Controller seviyesinde DTO üzerinden yıl kontrolü(year>2020)
```
curl --location --request POST 'localhost:8080/albums' \
--header 'Content-Type: application/json' \
--data-raw '{
  "id": 123,
  "title": "",
  "url": "http",
  "year": 2050,
  "active":false
}'
```

* ### Kaynaklar
    - https://mossgreen.github.io/Validations-in-Spring/
    - https://www.springboottutorial.com/spring-boot-validation-for-rest-services
    - https://phoenixnap.com/kb/spring-boot-validation-for-rest-services


[index için tıklayın](../README.md)
