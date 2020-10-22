* ### Notlar
    Bu çalışmadaki örnekler **com.ba.controller.ReportController** adlı class ta kodlanmıştır.
    
* ### Senaryo
    - İçinde çokça kayıt bulunan iki tablodan rapor çekileceği düşünülerek bu iki işlem farklı iki thread ile yapılmıştır.
    - Springte main thread den ayrı işlemler(async) yapmak için @Async anotasyonu kullanılır.
    - Bu caselere kurumsal uygulamarda çokça rastlanmaktadır.

* ### @EnableAsync (Async işlemlerin springte aktif edilmesi)
    Bu anotasyon ile spring'e main thread dışında da iş yaptırılacağı bildirilmiş olur. Anotasyon **com.ba.Run** class'ına eklenmiştir.
    ```
    @EnableCaching
    @EnableAsync
    @SpringBootApplication
    public class Run {...}
    ```

* ### @Configuration ve @Bean (Async işleri yapacak bean'in oluşturulması)
    Asenkron işlemler için springin **ThreadPoolTaskExecutor** classı kullanılmıştır.
    Bir configurasyon classı oluşturularak uygulama ayağa kalkarken **ThreadPoolTaskExecutor** bean'ini yaratması sağlanmıştır.
    
    ```
    @Configuration
    public class AsyncConfiguration {
    
        @Bean(name = "processExecutor")
        public TaskExecutor workExecutor() {
            ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
            threadPoolTaskExecutor.setThreadNamePrefix("AsyncThread-");
            threadPoolTaskExecutor.setCorePoolSize(3);
            threadPoolTaskExecutor.setMaxPoolSize(5);
            threadPoolTaskExecutor.setQueueCapacity(50);
            return threadPoolTaskExecutor;
        }
    }
    ```
    
    Üstteki tanımla uygulamadakı async threadlerin isimlendirmesi, max kaç thread aynı anda çalışacağı ve kuyruk kapasitesi tanımlanmıştır.
    
* ### @Async (asenkron işlerin koşulması)
    @Async anotasyonuna oluşturulan ThreadPoolTaskExecutor beaninin ismi String parametre olarak geçilerek Spring üzerinde async işlemler başlatılmış olur.

    Springte async taskların başlatan kısım aşağıdaki gibidir  
    ```
    @Async("processExecutor")
    public void generateTodoReports() throws Exception {...}
    ```

    Async işlemleri yapan kısım(tam method gövdesi)        
    ```
    @Async("processExecutor")
    public void generateTodoReports() throws Exception {
        log.info("Received request to generate TODO Report");

        for (int i = 0; i < 100; i++) {
            todoService.addNewTodo();
        }

        List<Todo> todos = todoService.findAllTodoList();

        pdfService.generateTodoList(TODO_FILE_PATH, todos);

        try {
            Thread.sleep(15 * 1000);
        } catch (InterruptedException ie) {
            log.error("Error in Export TodoList: {}", ie.getMessage());
        }

        log.info("TODO report generate operation completed");
    }
    ```    
    
* ### PdfService export methodlarının eklenmesi.
    **TodoListi** ve **GameList**'i pdf'e yazan iki method **PdfService**'ine eklenmiştir.
    Referansları aşağıdaki gibidir: <br/>
    **com.ba.service.PdfService.generateTodoList** <br/>
    **com.ba.service.PdfService.generateGameList**
    
* ### Endpointler ile erişim
    - TODO export için : http://localhost:8080/export-report/todo
    - Game export için : http://localhost:8080/export-report/game
    
    Üstteki endpointlere erişildikten sonra **target** dizininin altında **gameList.pdf** ve **todoList.pdf** adında rapor dosyaları da oluşacaktır.
    
    
* ### Notlar
    - Async işlemlerin gerçek senaryoya yaklaşması için Thread.sleep() ile 15 sn bekleme yapılmaktadır.
    - Art arda üstteki endpointlere erişildiğinde 15 sn sonra aşağıdaki loglar console dan görülür.
    ```
    2020-10-22 18:13:01.287  INFO 7163 --- [  AsyncThread-1] com.ba.service.ReportService             : GAME report generate operation completed
    2020-10-22 18:13:01.541  INFO 7163 --- [  AsyncThread-2] com.ba.service.ReportService             : TODO report generate operation completed
    ```     
    Üstteki loga bakıldığınd a**AsyncThread-1** ve **AsyncThread-2** iki farklı threadin iş yaptığını gösterir. <br/> 
    Yine saatteki **(2020-10-22 18:13:01)** bilgisi ise her iki thread in aynı anda çalıştığının diğer bir göstergesidir.
    
[index için tıklayın](../README.md)
