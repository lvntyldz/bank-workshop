###### NOT : Başladan önce cache server olarak redis kullanılacağı için ortamda redis kurulu değilse  redis kurulumu [doküman için tıklayın](./documentation/dockerRedisExample.md) adresi ziyaret edilmelidir.
###### NOT2 : application.properties file üzerinden redis cache aktif edilmemiştir. Bu örnekler uygulama üzerinden inceleneceği zaman aşağıdaki parametre aktif edilmelidir.
###### NOT3 : Redis cache'i aktif etmek için yine yorum'a çevrilen RedisCacheConfiguration class'ıda aktif java class'ına çevrilmelidir.
###### NOT4 : Redis cache'i aktif etmek için yine yorum'a çevrilen pom.xml dosyasındaki redis bağımlılıkları aktif edilmelidir.

```
#spring.cache.type=redis
```


* ### Referanslar
    Bu çalışmadaki örnekler **com.ba.controller.CarController** adlı classta  kodlanmıştır.

* ### Car işlemleri için Entity,Service,Controller ve Repository'nin oluşturulması
    - oluşturulan beanler aşağıdadır.
    - **com.ba.controller.CarController**
    - **com.ba.domain.Car**
    - **com.ba.repository.CarRepository**
    - **com.ba.service.CarService**

* ### Tanbloların oluşturulması ve sql insert işlemleri
    cachede kullanılamak üzere ihtyaç duyulan hazır veriler car tablosuna aşağıdaki şekilde uygulama ayağa kalkarken liquibase aracılığıyla insert edilecektir.
    ```
    INSERT INTO CAR (ID, MODEL_NAME, MODEL_YEAR, CREATE_DATE) VALUES (1, 'Ford', 2020, CURRENT_TIMESTAMP);
    INSERT INTO CAR (ID, MODEL_NAME, MODEL_YEAR, CREATE_DATE) VALUES (2, 'Opel',2010,  CURRENT_TIMESTAMP);
    INSERT INTO CAR (ID, MODEL_NAME, MODEL_YEAR, CREATE_DATE) VALUES (3, 'Renault',2000,  CURRENT_TIMESTAMP);
    INSERT INTO CAR (ID, MODEL_NAME, MODEL_YEAR, CREATE_DATE) VALUES (4, 'BMW',2019,  CURRENT_TIMESTAMP);
    INSERT INTO CAR (ID, MODEL_NAME, MODEL_YEAR, CREATE_DATE) VALUES (5, 'Volvo',2021,  CURRENT_TIMESTAMP);
    ```    


* ### Notlar
    - Entitynin cache object olarak atılabilmesi için serializable'ı implement etmesi gerekir.
    ```
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Table(name = "car")
    public class Car implements Serializable {...}
    ```
  
* ### Bağımlılıkların eklenmesi
    ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
    
    <dependency>
        <groupId>redis.clients</groupId>
        <artifactId>jedis</artifactId>
    </dependency>
    ```

* ### Cache provider'ın redis olarak ayarlanması
    application.properties dosyasına aşağıdaki parametre ekelenerek cache işlemlerinde redis'in kullanılması sağlanır.
    ```
    spring.cache.type=redis
    ```

* ### Configuration class'ının eklenmesi
    **RedisCacheConfiguration** adında bir class eklenerek burada **JedisConnectionFactory** ve **RedisTemplate** beanleri uygylama ayağa kalkarken oluşturulurlar.
    ```
    @Configuration
    @EnableCaching
    public class RedisCacheConfiguration {
    
        @Bean
        public JedisConnectionFactory jedisConnectionFactory() {
            return new JedisConnectionFactory(new RedisStandaloneConfiguration("localhost", 6379));
        }
    
        @Bean
        public RedisTemplate redisTemplate() {
            RedisTemplate redisTemplate = new RedisTemplate();
            redisTemplate.setConnectionFactory(jedisConnectionFactory());
            return redisTemplate;
        }
    }
    ```

* ### Cache'e yazma ve silme işlemi
    - Daha önce paylaşılan dokümanlarda Spring'in cacheinin nasıl kullanılacağı anlatılmıştı.
    - Spring cache kullanımı [doküman için tıklayın](./documentation/springCacheExamples.md)
    - Burada yine aynı cache anotasyonları kullanılacaktır.
    - Üstteki dokumandan farklı olarak inMemory cache yerine redis kullanılacaktır.

    Cache'e yazma
    ```
    @Cacheable(cacheNames = "CarCache")
    public Car getCarById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
    
        if (!optionalCar.isPresent()) {
            return null;
        }
    
        return optionalCar.get();
    }
    ```
        
    Cacheden silme
    ```
    @CacheEvict(cacheNames = "CarCache")
    public String deleteCarById(Long id) {
        carRepository.deleteById(id);
        return "ID : " + id + " olan içerik silindi";
    }
    ```

* ### Endpointler üzerinden erişim
    - Listeleme : http://localhost:8080/car/1
    - Silme : 
        ```
        curl --location --request DELETE 'http://localhost:8080/car/delete/1'
        ```

[index için tıklayın](../README.md)
