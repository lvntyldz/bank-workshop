* ### Referanslar
    Bu çalışmadaki örnekler **com.ba.controller.PostController** adlı class ta kodlanmıştır.
    
* ### unidirectional(tek yönlü)  ilişki 
    unidirectional ilişkide kaynak(source) entity den hedef(target) entity'e doğru erişim olur ancak target tan source'a doğru erişim olmaz.

* ### Create scriptlerinin liquibase'e eklenmesi
    - POST tablosunun oluşturulması
    ```
    CREATE TABLE POST (
        ID BIGINT NOT NULL AUTO_INCREMENT,
        TITLE VARCHAR(128),
        DESCRIPTION VARCHAR(128),
        PRIMARY KEY (ID)
    );
    ```
    
    - TAGS tablosunun oluşturulması
    ```
    CREATE TABLE TAGS (
        ID BIGINT NOT NULL AUTO_INCREMENT,
        NAME VARCHAR(128),
        PRIMARY KEY (ID)
    );
    ```
    
    - POST_TAGS adında ilişki tablosunun oluşturulması
    ```
    CREATE TABLE POST_TAGS (
        POST_ID BIGINT NOT NULL,
        TAG_ID BIGINT NOT NULL,
        PRIMARY KEY (POST_ID,TAG_ID)
    );
    ```

* ### Repository tanımının yapılması
    - POST repository tanımı
    ```
    public interface PostRepository extends JpaRepository<Post, Long> {...}
    ```
    
    - TAGS Repository tanımı
    ```
    public interface TagRepository extends JpaRepository<Tag,Long> {...}
    ```

* ### Entity lerin eklenmesi
    - Post isiminde entity oluşturma
    ```
    @Entity
    public class Post {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    
        private String title;
    
        private String description;
        
        ...
    }
    ```
    
    - Tag isminde entity oluşturma
    ```
    @Entity(name = "tags")
    public class Tag {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    
        private String name;
    
        ...
    }
    ```

* ### ManyToMany ilişkinin Book'a eklenmesi 
    - ManyToMany ilişkiler ara tabloda tutulur.
    - Burada unidirectional(tek yönlü)  ilişki kurulduğu için tanımlamalar sadece kaynak entity de yapılır. Hedef entity de bir tanıma ihtiyaç yoktur.
    - @JoinTable ile bir tablo ismi verilmezse hibernate iki tablonun isimleriyle bir ara tablo oluşturur.
        ```
        @JoinTable(name = "POST_TAGS",
        ```
    - joinColumns özelliğiyle  kaynak entitydeki PRIMARY_KEY alanının hangi isimde ara tabloya yazılacağını hibernate e bildirilir.
        ```
        joinColumns = @JoinColumn(name = "post_id")
        ```
    - inverseJoinColumns özelliğiyle ise hedef entitydeki PRIMARY_KEY alanının hangi isimde ilişki tablosuna yazılacağı belirtilir.
        ```
        inverseJoinColumns = @JoinColumn(name = "tag_id")
        ```
    
    - ManyToMany ilişkinin son hali aşağıdaki gibi olur
        ```
        @ManyToMany
        @JoinTable(name = "POST_TAGS", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
        private Set<Tag> tags = new HashSet<>();
        ```
    - **NOT**: entitydeki **PRIMARY_KEY** alanının hibernate tarafından bulunması yerine **referencedColumnName** özelliğiyle hibernate ilişkinin hangi alanlar üzerinden kurulacağı  bildirilebilir.
    ```
        @JoinTable(name = "POST_TAGS",
                joinColumns = @JoinColumn(name = "post_id",referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "tag_id",referencedColumnName = "id"))
    ```

* ### Endpointlere erişim
    - Post listesi : http://localhost:8080/post/list 
    - Tag listesi : http://localhost:8080/post/list-tags 
    - Post Ekleme : http://localhost:8080/post/add
    - Tag Ekleme : http://localhost:8080/post/add-tags
    - ID'ye göre Post  : http://localhost:8080/post/1 
    - ID'ye göre Post ve İlişki tablosundaki verileri silme
    ```
    curl --location --request DELETE 'localhost:8080/post/delete/2' --header 'Content-Type: application/json'
    ```    
    
* ### Hibernate logları
    - delete işleminde ID ye göre Entity bulmayı sağlayan SQL
    ```
    Hibernate: 
        select
            post0_.id as id1_14_0_,
            post0_.description as descript2_14_0_,
            post0_.title as title3_14_0_ 
        from
            post post0_ 
        where
            post0_.id=?
    ```
    - İlgili Post bulunduktan sonra önce ilişkiyi silen SQL 
    ```
    Hibernate: 
        delete 
        from
            post_tags 
        where
            post_id=?
    ```      
    - İlişki silindikten sonra POST tablosundaki veriyi silen SQL
    ```
    Hibernate: 
        delete 
        from
            post 
        where
            id=?
    ```      

[index için tıklayın](../README.md)
