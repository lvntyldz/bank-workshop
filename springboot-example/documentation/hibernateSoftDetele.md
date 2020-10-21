* ### Notlar
    Soft delete DB den kaydı silmeden bir flag aracılığıyla kaydı pasif hale çekmektir.
    Buradaki çalışmada **Todo** adlı bir entity oluşturulmuştur. Todo daki kayıtların silinmesi durumunda  **deleted** kolonu 1 yapılacaktır.
    Listelemelerde ise deleted alanı 0(sıfır) olanlar gösterilecektir.
     
* ### Todo adlı Entity oluşturma
    ```
    @Entity
    @Table(name = "todos")
    @SQLDelete(sql =
            "UPDATE todos " +
                    "SET deleted = true " +
                    "WHERE id = ?")
    @Where(clause = "deleted = false")
    public class Todo implements Serializable {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    
        private String title;
    
        private String description;
    
        private boolean deleted;
        ...
    }
    ``` 

    **@Where** anotasyonu ile sadece deleted=0 olanlar üzerinde işlem yapılacaktır.
    ```
    @Where(clause = "deleted = false")
    ``` 
    @SQLDelete anotasyonu ile silme işleminde çalıştırılacak sorgu belirtilir. Bu sordu ile kayıt DB den silinmez sadece deleted=1 yapılır.
    ```
    @SQLDelete(sql =
            "UPDATE todos " +
                    "SET deleted = true " +
                    "WHERE id = ?")
    ```
    
* ### endpoint, service ve repository oluşturma
    Daha önce anlatıldığı gibi service,controller ve repository katmanları oluşturulur.
    Controller örneği aşağıdaki gibi olacaktır
    ```
    @RestController
    @RequestMapping("todo")
    public class TodoController {
    
        @Autowired
        private TodoService service;
    
        @GetMapping("/add")
        public String addTodo() {
            return service.addNewTodo();
        }
    
        @GetMapping("/list")
        public List<Todo> getAllTodo() {
            return service.findAllTodoList();
        }
    
        @GetMapping("/{id}")
        public Todo getTodoById(@PathVariable Long id) {
            return service.findTodoById(id);
        }
    
        @DeleteMapping("/delete/{id}")
        public String deleteTodo(@PathVariable Long id) {
            return service.deleteTodoById(id);
        }
    }
    ```
    
* ### endpoint aracılığıyla kayıt silme
    ID:3 olan Todo'yu DB den silmek için aşağıdaki komut terminalden çalıştırılır
    ```
    curl --location --request DELETE 'http://localhost:8080/todo/delete/3'
    ```
    
    Bu komutun çalıştırılmasından sonra hibernate loglarında aşağıdaki UPDATE cümlesinin olduğu görülür.
    ```
    Hibernate: 
        UPDATE
            todos 
        SET
            deleted = true 
        WHERE
            id = ?
    2020-10-12 17:30:29.043 TRACE 8548 --- [nio-8080-exec-2] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [BIGINT] - [3]
    2020-10-12 17:30:29.046  INFO 8548 --- [nio-8080-exec-2] i.StatisticalLoggingSessionEventListener : Session Metrics {
    ```
    
* ### H2-console üzerinden kaydın durumunun görüntülenmesi
    http://localhost:8080/h2-console adresine gidilerek  H2-Console üzerinden DB'ye bağlanılır. 
    Daha sonra aşağıdaki SQL sorgusu çalıştırıldığında 3 nolu Todo nun deleted alanı 1 olararak güncellendiği gözlemlenir.
    ```
    SELECT * FROM TODOS 
    ```

[index için tıklayın](../README.md)
