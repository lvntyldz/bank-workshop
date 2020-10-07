* ### unidirectional(tek yönlü)  ilişki 
    unidirectional ilişkide kaynak(source) entity den hedef(target) entity'e doğru erişim olur ancak target tan source'a doğru erişim olmaz.

* ### Create scriptlerinin liquibase'e eklenmesi
    create-book-table.xml ve create-page-table.xml dosyaları daha önce gerekli configurasyonları yapılan master.xml dosyasına eklenir.
    ```
    <databaseChangeLog>
        <include file="/db-changelog/changes/create-book-table.xml"/>
        <include file="/db-changelog/changes/create-page-table.xml"/>
    </databaseChangeLog>    
    ```

* ### Repository tanımının yapılması
    Book DB işlemlerinin yapılabilmesi için JpaRepository den extends edilecek şekilde BookRepository adında bir interface oluşturulur
    ```
    @Repository
    public interface BookRepository extends JpaRepository<Book, Long> {
    
    }
    ```

* ### Entity lerin eklenmesi
    Kitap ve sayflarını turacak şekilde Page ve Book adında birer enity oluşturulur. 
    ```
    @Entity
    @Table(name = "books")
    public class Book implements Serializable {
    ...
    }
    ```

    ```
    @Entity
    @Table(name = "pages")
    public class Page implements Serializable {  
    ...
    }
    ```


* ### OneToMany ilişkinin Book'a eklenmesi 
    Oluşturulan Book entitysi ile Page entitysi arasında one to many ilişki kurmak için  <b>@OneToMany</b> annotation'ı kullanılır.
    ```
    @Entity
    @Table(name = "books")
    public class Book implements Serializable {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    
        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name="book_id")
        private Set<Page> pages;
    }
    ```
  - <b> cascade = CascadeType.ALL : </b> üst entity eklendiği,silindiği,refresh edildiği zaman alt entity de buradaki aksiyondan birebir etkilenir.
  - <b> @JoinColumn(name="book_id") : </b> Burada Book ve Page ilişkisinin book_id ile tutulacağı belirtilmiş olur. PAGES tablosunda BOOK_ID adında bir sütun oluşturulmalıdır.

###### NOTE: Eğer @JoinColumn kullanılmadan aşağıdaki gibi sadece @OneToMany satırındaki tanım yapılsaydı bu durumda hibernate varsayılan olarak PAGES ve BOOKS tablolarını ilişkilendirmek için BOOK_PAGES adında bir ilişki tablosu(ara tablo) arayacaktı. Ve ilişkiler ara tablo üzerinden tanımlanacaktı.   
@JoinColumn kullanılmasıyla birlikte yeni bir tabloya ihtiyaç duymadan mevcut entity'e bir ilişki sütunu eklenerek ilişkiler yönetilir.

```
@Entity
@Table(name = "books")
public class Book implements Serializable {
    ... 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Page> pages;
    ...
}
```

* ### Endpointlere erişim
    <b>Kitap eklemek için  : </b> http://localhost:8080/book/add

     <b>Tüm kitap listesi için : </b> http://localhost:8080/book/list

     <b>ID'ye göre kitap listelemek için : </b> http://localhost:8080/book/2

     <b>Kitap ve Sayfalarını silme işlemi </b>
    ```
    curl --location --request DELETE 'http://localhost:8080/book/delete/1'
    ```

[index için tıklayın](../README.md)
