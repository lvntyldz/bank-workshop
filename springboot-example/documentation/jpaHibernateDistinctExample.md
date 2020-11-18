* ### Referanslar
    Bu çalışmadaki örnekler **com.ba.controller.NewsController** adlı class ta kodlanmıştır.
    
* ### Açıklamalar
    - Bu çalışmada haber ve haber kategorileri üzerinden işlem yapılmıştır.
    - Haberler girilirken haber kategorileri de String olarak DB ye insert edilmiştir.
    - Birden fazla haber aynı kategoride olabileceği için category_name unique değildir.
    - Haber kategorilerinin unique olarak listeleneceği bir endpoint(http://localhost:8080/news/list/categories) yazılmıştır.
    
* ### Varsayılan kayıtların insert işlemi 
    resources/db-changelog/changes/scripts/insert.sql dosyasında uygulama ayağa kalkarken farklı kategorilerde kayıt için birkaç insert işlemi yapılmıştır.
    ```
    INSERT INTO NEWS (ID, TITLE, CATEGORY_NAME, DESCRIPTION) VALUES (1, 'Bir istifa daha','siyaset','Ekonomi bakanı istifa etti');
    INSERT INTO NEWS (ID, TITLE, CATEGORY_NAME, DESCRIPTION) VALUES (2, 'dolar düştü','ekonomi','Ekonomi bakanı istifa etti dolar düşüşe geçti');
    INSERT INTO NEWS (ID, TITLE, CATEGORY_NAME, DESCRIPTION) VALUES (3, 'Teknoloji hamlesi','teknoloji','daha çok inşaat, daha çok AR-GE');
    INSERT INTO NEWS (ID, TITLE, CATEGORY_NAME, DESCRIPTION) VALUES (4, 'ev fiyatları uçtu','ekonomi','Dolar,euro, altın düşüyor ancak ev fiyatları yükseliyor');
    ```
  
* ### @Query ile DISTINCT kullanımı
    NewsRepository katmanında kategorileri distinct olarak çekecek bir method eklenmiştir. Bu method List<String> olarak kategori isimlerini döner.
    ```
    @Query("SELECT DISTINCT n.categoryName FROM News n")
    List<String> getAllUniqueCategories();
    ```
  
* ### Category Listi dönecek endpoint
    Controller katmanında tüm kategorileri dönmesi için aşağıdaki şekilde bir endpoint eklenmiştir.
    ```
    @GetMapping("/list/categories")
    @ApiOperation(value = "Read all news categories", notes = "Will get all the news categories")
    public List<String> getAllNewsCategories() {
        return service.findAllNewsCategoryList();
    }
    ```
* ### Tüm endpoint işlemleri
    - Yeni haber ekleme : http://localhost:8080/news/add
    - Tüm haberleri listeleme : http://localhost:8080/news/list
    - Tüm haber kategorilerini listeleme  : http://localhost:8080/news/list/categories
    - ID ye göre haber içeriği çekme : http://localhost:8080/news/2
    - ID ye göre haber silme : 
    ```
    curl --location --request DELETE 'localhost:8080/news/delete/1' \
    --header 'Content-Type: application/json' \
    --data-raw ''
    ```      
   
[index için tıklayın](../README.md)
