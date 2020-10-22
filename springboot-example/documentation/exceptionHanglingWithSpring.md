* ### Referanslar
    Bu çalışmadaki örnekler **com.ba.controller.PersonControllerr** adlı classta  kodlanmıştır.

* ### Açıklamalar
    Spring Exceptionların tek yerden handle edebilmesine olanak sağlar.
    Eğer global bir exception handling mekanizması kurulmazsa her throw edilen exception için bir response hazılanması gerekir. Bu da gereksiz kod ve efor anlamına gelir.
    
    

* ### Sisteme ait exception ların tanımlanması
    ilk olarak geliştirilen sistemin kendi exception classları eklenir. Bu çalışmada **SystemException** ve **BusinessRuleException** adında iki exception class'ı oluşturulmuştur.
    ```
    public class SystemException extends RuntimeException {
    
        public SystemException(String message) {
            super(message);
        }
    }
    ```

* ### ResponseDTO'nun tanımlanması
    Herhangi bir Exception oluştuğunda aynı ortak mesaj yapısında bir cevap verebilmek için  ErrorResponseDTO adında bir DTO class'ı oluşturulur.
    ```
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class ErrorResponseDTO {
        private Date timestamp;
        private String message;
        private String details;
    }
    ```
* ### @ControllerAdvice anotasyonu
    - Bu anotasyon tüm uygulamadaki exceptionların ele alınmasını sağlar.
    - Bunu atılan exceptionlarda araya giren bir interceptor olarak değerlendirmek mümkündür.  
    - Yakalanılan exceptionların bir Rest endpoint aracılığıyla istenen sonucun dönmesi sağlanabilir.
    - Yakalanan exceptionların rest endpoint olarak cevaplandırılabilmesi için class @RestController ile işaretlenir.
    ```
    @RestController
    @ControllerAdvice
    public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {...}
    ```

* ### @ExceptionHandler anotasyonu
    - Bu anotasyon ile Yakalanan exceptionlar handle edilebilir. 
    - Methodun üsütne yazılan  **@ExceptionHandler**  anotasyonuyla gövdesi doldurulan method ve oluşan Exception ilişkilendirilir.
    ```
    @ExceptionHandler(BusinessRuleException.class)
    public final ResponseEntity<ErrorResponseDTO> handleBusinessRuleException(BusinessRuleException e, WebRequest request) {
        ErrorResponseDTO response = prepareResponseModel(e.getMessage(), request);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    ```
  
  Buradaki örnekte her **BusinessRuleException** oluştuğunda sadece mesaj içeriği değişerek tüm **endpointlerden** aynı yapıda bir sonuç dönülür.

* ### Endpointler üzerinden erişim
    - Yaşı random üretilen yeni bir person eklemek için : http://localhost:8080/person/add 
    Burada **com.ba.service.PersonService.addNewPerson** adlı class ta person.age random olarak oluşturulmaktadır.
    Eğer age>100 yada age<0 ise **BusinessRuleException** atılmaktadır.
    
    - ID<0 olan bir person'u silme : 
        ```
        curl --location --request DELETE 'http://localhost:8080/person/delete/-1'
        ```    
        response aşağıdaki gibi olacaktır
        ```
        {
            "timestamp": "2020-10-22T11:45:07.204+00:00",
            "message": "ID sıfırdan büyük olmalıdır!",
            "details": "uri=/person/delete/-1"
        }
        ```            
* ### DB sorgulama
    Eklenen kayıtların görüntülenebilmesi için h2-console(http://localhost:8080/h2-console) üzerinden aşağıdaki sorgu çalıştırılabilir.    
    ```
    SELECT * FROM PERSONS 
    ```

[index için tıklayın](../README.md)
