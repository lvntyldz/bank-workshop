* ### Açıklama
    Bu çalışma **Game** class'ı üzerinde gerçekleştirilmiştir.
    Entity'e bağlı olarak **service,controller ve repository** katlanları da oluşturulmuştur.
    Kod içerisinde tekrarlı olarak yapılan bir takım işleri run time da lombok yapabilir.
    
    örn: aşağıdaki kısımlar lombok ile oluşturulabilir
    - getter-setter methodları
    - constructorlar
    - loglama işlemleri
    - null-check işlemi
    - toString işlemi

* ### Bağımlılık
    aşağıdaki şekilde maven projesine eklenir.
    ```
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    ```

* ### Loglama
    loglma işlemleri için lombok aşağıdaki şekilde kullanılır.
    ```
    log.info("game deleting...id:{}", id);
    ```
    id'den sonraki süslü parantezler içine mesajdan sonraki ilk parametre gelecektir. Bu işlem tekrarlı olarak(2. ve 3. parametre şekline) devam eder.

* ### Getter-Setter
    Class'ın tüm değişkenlerine getter-setter eklemek için aşağıdaki şekilde tanımlama yapılır.
    ```
    @Data
    public class Game extends BaseEntity {...}
    ```
    @Data hem getter hem de setter ekler. sadece getter yada sadece setter eklemek için @Getter  ve @Setter anotasyonları kullanılır.
    
    Setter örneği
    ```
    @Setter
    public class Game extends BaseEntity {...}
    ```
    
    Getter örneği
    ```
    @Getter
    public class Game extends BaseEntity {...}
    ```

* ### Constructor
    Yazılan classın tüm parametrelerini içeren yada hiç parametre içermeyen(default) constructor'ı lombok ile oluşturulabilir.
    ```
    @AllArgsConstructor
    @NoArgsConstructor
    public class Game extends BaseEntity {...}
    ```

* ### Game classı
    Yazılan Game classının içeriği aşağıdaki gibidir.
    ```
    @Entity
    @Table(name = "game")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public class Game extends BaseEntity {
    
        private String name;
    
        private String description;
    
        private long roundNo;
    
        private long sessionId;
    
        private long leagueId;
    
        private boolean postponed;
    
        private boolean played;
    
        @Temporal(TemporalType.TIMESTAMP)
        private Date createDate;
    
    }
    ```
* ### End-point ler üzerinden işlem
    ekleme işlemi için 
    http://localhost:8080/game/add
    
    Get by ID işlemi için 
    http://localhost:8080/game/1
    
    listelem işlemi için 
    http://localhost:8080/game/list
    
    silme işlemi için 
    curl --location --request DELETE 'http://localhost:8080/game/delete/2'

[index için tıklayın](../README.md)
