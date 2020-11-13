* ### Referanslar
    Bu çalışmadaki örnekler **spring-request-mapping** adlı projede kodlanmıştır.
    
* ### Açıklamalar
    Bu çalışmada spring boot ile **GetMapping, PostMapping, DeleteMapping** ve **PutMapping** işlemleri örneklenmiştir.
    Çalışmada id,title ve description  News adında bir DTO oluşturulmuştur. 
    ```
    public class News {
        private Long id;
        private String title;
        private String description;
        ...
    }
    ```

    **NewsController.java** da allNews adında bir değişken oluşturularak haber ekleme, silme, güncelleme ve listeleme işlemleri buradaki değişken üzerinden yürütülmektedir.
    ```
    private List<News> allNews = new ArrayList<>();
    ```


* ### getNewsById
    ```
    @GetMapping("/{id}")
    public News getNewsById(@PathVariable Long id) {
    ...
    }
    ```

* ### listAllNews
    ```
    @GetMapping("/list")
    public List<News> listAllNews() {
        return allNews;
    }
    ```

* ### addNews
    ```
    @PostMapping("/add")
    public News addNews(@RequestBody News news) {
        allNews.add(news);
        return news;
    }
    ```

* ### updateNewsById
    ```
    @PutMapping("/update/{id}")
    public News updateNews(@PathVariable Long id, @RequestBody News news) {
    ...
    }
    ```

* ### deleteNewsById
    ```
    @DeleteMapping("/{id}")
    public List<News> deleteNews(@PathVariable Long id) {
        allNews.removeIf(news -> news.getId() == id);
        return allNews;
    }
    ```

[index için tıklayın](../README.md)
