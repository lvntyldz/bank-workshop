* ### Referanslar
    Bu çalışmadaki örnekler **com.ba.controller.SessionController** adlı class ta kodlanmıştır.
 
* ### Açıklamalar
    - Http stateless bir protokoldür. Yani Server tarafında client ile ilgili bir bilgi tutulmaz. Yani bilgiler server'a gerektiğinde client tarafından sunulur.
    - Bir önceki request bir sonrakinin verisiyle ilgilenmez.
    - Dolayısıyla bir takım verileri requestler arasında taşımak için her requestin içine tek tek  eklemek yerine session özelliği kullanılabilir(REST te tavsiye edilmez). 
    - Springde gelen Http Requestleri ile bir session oluşturmak istenirse varsayılan olarak veriler Uygulama sunucusunda(tomcat) tutulur.
    - Spring bu confgurasyonu değiştirme olanağı sunar.
    - Uygulama sunucusunda verilerin tutulabileceği gibi DB'ye de yazılabilir.

* ### Konfigürasyonlar
    - application.properties dosyasına aşağıdaki iki parametre eklenmelidir.  
    ```
    spring.session.store-type=jdbc
    spring.session.jdbc.initialize-schema=always
    ```
    - **spring.session.store-type** : Session  verilerinin nerede tutulacağını belirler. **default**'un(uygulama sunucusu) yanında **Jdbc**, **Redis** ve **MongoDb** olarak tanımlanabilir.
    - **spring.session.jdbc.initialize-schema** : Session verileri DB de tutulacağı zaman **SPRING_SESSION** ve **SPRING_SESSION_ATTRIBUTES** tablolarına ihtiyaç duyulmaktadır. Bu parametre ile bahsi geçen iki tablo DB de create edilir. create table scriptleri **org.springframework.session.jdbc** paketinin altındadır.

* ### Bağımlılıklar
    - Spring ile session bilgilerinin DB üzerinden tutulabilmesi için **spring-session-jdbc** 'nin pom.xml dosyasına eklenmesi gerekir.
    ```
    <dependency>
        <groupId>org.springframework.session</groupId>
        <artifactId>spring-session-jdbc</artifactId>
    </dependency>
    ```

* ### Session'a Yazma ve Okuma 
    - Sessiondan değer okuma 
    ```
    session.getAttribute("favoriteColors");
    ```
    
    - Sessiona değer yazma 
    ```
    request.getSession(true).setAttribute("favoriteColors", favoriteColors);
    ```
    
    - Session'u temizleme 
    ```
    request.getSession().invalidate();
    ```
    
    - Üstteki bilgileri de kapsayan SessionController'ın içeriği aşağıdaki gibi olacaktır.
    ```
        @GetMapping(value = "/list")
        public String getFromSession( HttpServletRequest request) {
            List<ColorDTO> favoriteColors = getFavColors(request.getSession());
            return String.format("SessionId :  %s  \n  favoriteColors: %s ", request.getSession().getId(), favoriteColors);
        }
    
        @PostMapping(value = "/save")
        public String saveToSession(@RequestBody ColorDTO dto, HttpServletRequest request) {
            List<ColorDTO> favoriteColors = getFavColors(request.getSession());
    
            if (dto != null && dto.getColorName() != null) {
                favoriteColors.add(dto);
                request.getSession(true).setAttribute("favoriteColors", favoriteColors);
            }
    
            return "session updated";
        }
    
        @GetMapping(value = "/destroy")
        public String destroySession( HttpServletRequest request) {
            request.getSession().invalidate();
            return "session clear";
        }
    
        private List<ColorDTO> getFavColors(HttpSession session) {
            List<ColorDTO> favoriteColors = (List<ColorDTO>) session.getAttribute("favoriteColors");
    
            if (favoriteColors != null) {
                return favoriteColors;
            }
            return new ArrayList<>();
        }
    }
    ```

* ### Notlar
    - Objenin Sessionda tutulabilmesi için Serializable dan impelemnt edilmesi gerekir
    ```
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class ColorDTO implements Serializable {
        private String colorName;
        private String colorCode;
    }
    ```

* ### Endpointler
    - GET from Session : 
    ```
    curl --location --request GET 'http://localhost:8080/session/list' \
    --header 'Cookie: SESSION=MjljMjg4MzQtMmFlNC00ODY3LThjODgtZmU5YTI4MjA2N2I1; JSESSIONID=253BD89D1E93E707EAE7A86DBEB49157'
    ``` 
    
    - Write to Session : 
    ```
    curl --location --request POST 'http://localhost:8080/session/save' \
    --header 'Content-Type: application/json' \
    --header 'Cookie: SESSION=MjljMjg4MzQtMmFlNC00ODY3LThjODgtZmU5YTI4MjA2N2I1; JSESSIONID=253BD89D1E93E707EAE7A86DBEB49157' \
    --data-raw '{
        "colorName":"red",
        "colorCode":"#we234"
    }'
    ```

* ### SQL sorguları
    - Aşağıdaki SQL sorguları ile  session tablolarındaki veriler görüntülenebilir  
    ```
    SELECT * FROM SPRING_SESSION 
    ```
    
    ```
    SELECT * FROM SPRING_SESSION_ATTRIBUTES  
    ```

* ### Kaynaklar
    - https://examples.javacodegeeks.com/spring-boot-session-management/
    - https://www.baeldung.com/spring-session-jdbc

[index için tıklayın](../README.md)
