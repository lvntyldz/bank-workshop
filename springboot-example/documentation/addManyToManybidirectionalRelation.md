* ### Referanslar
    Bu çalışmadaki örnekler **com.ba.controller.CategoryController** adlı class ta kodlanmıştır.
    
* ### bidirectional(çift yönlü)  ilişki 
    bidirectional ilişkide kaynak(source) entity den hedef(target) entity'e doğru erişim olduğu gibi.target tan source'a doğruda erişim olur.

* ### Create scriptlerinin liquibase'e eklenmesi
    - CATEGORY tablosunun oluşturulması
    ```
    CREATE TABLE TBL_CATEGORIES (
        ID BIGINT NOT NULL AUTO_INCREMENT,
        NAME VARCHAR(128),
        DESCRIPTION VARCHAR(128),
        PRIMARY KEY (ID)
    );
    ```
    
    - PRODUCT tablosunun oluşturulması
    ```
    CREATE TABLE TBL_PRODUCT (
        ID BIGINT NOT NULL AUTO_INCREMENT,
        NAME VARCHAR(128),
        PRICE BIGINT,
        PRIMARY KEY (ID)
    );
    ```
    
    - CATEGORY_PRODUCT adında ilişki tablosunun oluşturulması
    ```
    CREATE TABLE TBL_CATEGORY_PRODUCT (
        CATEGORY_ID BIGINT NOT NULL,
        PRODUCT_ID BIGINT NOT NULL,
        PRIMARY KEY (CATEGORY_ID,PRODUCT_ID),
        CONSTRAINT PRODUCT_ID_FK FOREIGN KEY (PRODUCT_ID) REFERENCES TBL_PRODUCT(ID),
        CONSTRAINT CATEGORY_ID_FK FOREIGN KEY (CATEGORY_ID) REFERENCES TBL_CATEGORIES(ID)
    );
    ```

###### NOTE
Burada **FOREIGN_KEY** ler tanımlanmazsa veritabanı tarafında veri bütünlüğü sağlanamaz. 
Örn: Veritabanınan console üzerinden bağlanan bir kullanıcı  **TBL_CATEGORY_PRODUCT** tablosunda **PRODUCT_ID:1** olan bir ilişki varken **TBL_PRODUCT** tablosundan **ID:1** olan product kaydını silebilir. 
Bu durumda ana tabloda kayıt olmamasına rağmen ara tabloda ilişki kaydı tutulmaya devam eder. 
Bu sorunların önüne geçmek için **FOREIGN_KEY** ler kullanılır. 
**FOREIGN_KEY ler kullanıcıyı önce ilişkiyi kaldırmaya zorlarlar.**
  
```
CONSTRAINT PRODUCT_ID_FK FOREIGN KEY (PRODUCT_ID) REFERENCES TBL_PRODUCT(ID),
CONSTRAINT CATEGORY_ID_FK FOREIGN KEY (CATEGORY_ID) REFERENCES TBL_CATEGORIES(ID)
```

* ### Repository tanımının yapılması
    - Category repository tanımı
    ```
    public interface CategoryRepository extends JpaRepository<Category, Long> {...}
    ```
    
    - Product Repository tanımı
    ```
    public interface ProductRepository extends JpaRepository<Product, Long> {...}
    ```

* ### Entity lerin eklenmesi
    - Category isiminde entity oluşturma
    ```
    @Entity(name = "TBL_CATEGORIES")
    public class Category {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    
        private String name;
    
        private String description;
    
        ...
    }
    ```
    
    - Product isminde entity oluşturma
    ```
    @Entity(name = "TBL_PRODUCT")
    public class Product {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    
        private String name;
    
        private int price;
        ...
    }
    ```

* ### ManyToMany ilişkinin Book'a eklenmesi 
    - ManyToMany ilişkiler ara tabloda tutulur.
    - Burada bidirectional(çift yönlü)  ilişki kurulduğu için tanımlamalar hem kaynak hem de hedef entity de yapılır.    
    - @JoinTable ile bir tablo ismi verilmezse hibernate iki tablonun isimleriyle bir ara tablo oluşturur.
        ```
        @JoinTable(name = "TBL_CATEGORY_PRODUCT"
        ```
    - joinColumns özelliğiyle  kaynak entitydeki **PRIMARY_KEY** alanının hangi isimde ara tabloya yazılacağını hibernate e bildirilir.
        ```
        joinColumns = @JoinColumn(name = "category_id")
        ```
    - inverseJoinColumns özelliğiyle ise hedef entitydeki **PRIMARY_KEY** alanının hangi isimde ilişki tablosuna yazılacağı belirtilir.
        ```
        inverseJoinColumns = @JoinColumn(name = "product_id")
        ```
    
    - ManyToMany ilişkinin son hali kaynak entity de  aşağıdaki gibi olur
        ```
        @JsonManagedReference
        @ManyToMany
        @JoinTable(name = "TBL_CATEGORY_PRODUCT", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
        private Set<Product> products = new HashSet<>();
        ```

    - Hedef entity de ise sadece mappedBy annotation ı ile kaynak entity deki değişken isminin verilmesi yeterlidir.
    ```
    @JsonBackReference
    @ManyToMany(mappedBy = "products")
    private Set<Category> categories = new HashSet<>();
    ```
###### NOTE
Kaynak entitydeki @JsonManagedReference ve hedef entitydeki @JsonBackReference annotationları jackson'un Infinite Recursion probleminden kurtulmak için eklenmiştir.
Java objeleri üzerinde çift yönlü ilişki bu anotasyonları kullanmadan da kurulmuş olmaktadır.  
Çift yönlü bağın kurulduğunu görmek için aşdağıdaki Kategori ve Product listesinden dönen Entityler debug edilerek incelenebilir.
- Category listesi : http://localhost:8080/category/list 
- Product listesi : http://localhost:8080/category/list-products 
  
* ### Endpointlere erişim
    - Category listesi : http://localhost:8080/category/list 
    - Product listesi : http://localhost:8080/category/list-products 
    - Category Ekleme : http://localhost:8080/category/add
    - Product Ekleme : http://localhost:8080/category/add-products
    - ID'ye göre category  : http://localhost:8080/category/1 
    - ID'ye göre category ve İlişki tablosundaki verileri silme
    ```
    curl --location --request DELETE 'localhost:8080/category/delete/2' --header 'Content-Type: application/json'
    ```    
    
###### NOTE: 
ManyToMany ilişkide  bidirectional(çift yönlü)  ilişki kurmanın unidirectional(tek yönlü) ilişkiden tek farkı hedef entity'e giderek kaynak entity için mappedBy özelliğinin tanımlanmasıdır.
```
@JsonBackReference
@ManyToMany(mappedBy = "products")
private Set<Category> categories = new HashSet<>();
```
    
    
[index için tıklayın](../README.md)
